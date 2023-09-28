package com.onsystem.wscapp.pantheon;

import com.onsystem.wscapp.pantheon.api.interfaces.exceptions.InfoException;
import com.onsystem.wscapp.pantheon.api.response.language.ErrorValidationObject;
import com.onsystem.wscapp.pantheon.model.TranslatorUtils;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.LocaleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
@Validated
public class RestExceptionHandler {

    private static final String NAME_CONTROLLER_PACKAGE = "controller";
    @Autowired
    private TranslatorUtils translatorUtils;

    @ExceptionHandler(InfoException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GenericView<String> handlerInfoException(final InfoException infoException) {
        return GenericView.<String>builder()
                .response(infoException.getMessage())
                .build();
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GenericView<?> handleValidationExceptions(ConstraintViolationException ex) {
        //TODO get locale to session users
        //TODO validation bbdd
        final Locale locale = LocaleUtils.toLocale("es");

        final boolean isErrorValidationInController = ex.getConstraintViolations().stream()
                .findFirst()
                .map(cv -> cv.getRootBeanClass().getPackageName().contains(NAME_CONTROLLER_PACKAGE))
                .orElse(false);

        if (isErrorValidationInController) {
            final Function<Iterator<Path.Node>,String> fnLastPropertyNameInPath = nodes -> {
              String propertyName = null;
              while (nodes.hasNext()){
                  final Path.Node node = nodes.next();
                  if(!nodes.hasNext())
                      propertyName = node.getName();
              }
              return propertyName;
            };

            final Map<Object, List<ConstraintViolation<?>>> mapGroupObjectViolations = ex.getConstraintViolations().stream()
                    .collect(Collectors.groupingBy(ConstraintViolation::getLeafBean));

            //Cache para no repetir procesos simultaneos
            final Map<ConstraintViolation<?>,String> mapCacheContainLastProperty = mapGroupObjectViolations.values().stream()
                    .flatMap(Collection::stream)
                    .collect(Collectors.toMap(
                            constraintViolations -> constraintViolations,
                            o -> fnLastPropertyNameInPath.apply(o.getPropertyPath().iterator()),
                            (s, s2) -> s)
                    );

            // Agrupar violaciones por objeto y propiedad
            final Map<Object, Map<String, List<String>>> mapGroupObjectAndWithProperties = mapGroupObjectViolations.entrySet().stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            entry -> entry.getValue().stream()
                                    .collect(Collectors.groupingBy(
                                            mapCacheContainLastProperty::get,
                                            Collectors.mapping(o ->
                                                    translatorUtils.createMessage(o.getMessage(),locale,mapCacheContainLastProperty.get(o)), Collectors.toList())
                                    ))
                    ));

            final List<ErrorValidationObject<Object>> errorValidationObjects = mapGroupObjectAndWithProperties.entrySet()
                    .stream()
                    .map(entry -> ErrorValidationObject.builder()
                            .object(entry.getKey())
                            .messageErrors(entry.getValue()).build())
                    .toList();

            //Return validation controller error
            return GenericView.<List<ErrorValidationObject<Object>>>builder()
                    .response(errorValidationObjects)
                    .build();
        }


        //TODO Custom error
        return null;

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> error(final Exception exception) {
        log.error(exception.getMessage());
        return ResponseEntity.internalServerError()
                .build();
    }
}
