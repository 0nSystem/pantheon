package com.onsystem.wscapp.pantheon.controllers.applications;

import com.onsystem.wscapp.pantheon.GenericView;
import com.onsystem.wscapp.pantheon.api.dto.applications.attribute.*;
import com.onsystem.wscapp.pantheon.api.interfaces.services.applications.create.ICreateAttributeService;
import com.onsystem.wscapp.pantheon.api.interfaces.services.applications.delete.IDeleteAttributeService;
import com.onsystem.wscapp.pantheon.api.interfaces.services.applications.update.IUpdateAttributeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static com.onsystem.wscapp.pantheon.api.interfaces.Constants.ENDPOINT_ATTRIBUTE_ROOT_CONTROLLER;
import static com.onsystem.wscapp.pantheon.api.interfaces.Constants.ENDPOINT_TO_LANGUAGE;

@RestController
@RequestMapping(ENDPOINT_ATTRIBUTE_ROOT_CONTROLLER)
@Validated
public class AttributeController {

    @Autowired
    private ICreateAttributeService iCreateAttributeService;
    @Autowired
    private IUpdateAttributeService iUpdateAttributeService;
    @Autowired
    private IDeleteAttributeService iDeleteAttributeService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericView<Set<AttributeWithLanguagesDTO>> createAttribute(
            final @RequestParam @Valid @Positive Integer applicationId,
            final @RequestBody @Valid @NotEmpty Set<CreateAttributeWithLanguageDTO> createAttributeWithLanguage
    ) {
        return GenericView.<Set<AttributeWithLanguagesDTO>>builder()
                .response(iCreateAttributeService.createAttributesWithLanguages(applicationId, createAttributeWithLanguage))
                .build();
    }

    @PostMapping(ENDPOINT_TO_LANGUAGE)
    @ResponseStatus(HttpStatus.OK)
    public GenericView<Set<AttributeLanguageDTO>> createAttributeLanguage(
            final @RequestParam @Valid @Positive Integer attributeId,
            final @RequestBody @Valid @NotEmpty Set<CreateAttributeLanguageDTO> createAttributeLanguage
    ) {
        return GenericView.<Set<AttributeLanguageDTO>>builder()
                .response(iCreateAttributeService.createAttributesLanguages(attributeId, createAttributeLanguage))
                .build();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateAttribute(
            final @RequestBody @Valid @NotEmpty Set<UpdateAttributeDTO> updateAttribute
    ) {
        iUpdateAttributeService.updateAttribute(updateAttribute);
    }

    @PutMapping(ENDPOINT_TO_LANGUAGE)
    @ResponseStatus(HttpStatus.OK)
    public void updateAttributeLanguage(
            final @RequestBody @Valid @NotEmpty Set<UpdateAttributeLanguageDTO> updateAttributeLanguage
    ) {
        iUpdateAttributeService.updateAttributeLanguages(updateAttributeLanguage);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAttribute(
            final @RequestParam @Valid @NotEmpty List<Integer> idsAttributes
    ) {
        iDeleteAttributeService.deleteAttribute(idsAttributes);
    }

    @DeleteMapping(ENDPOINT_TO_LANGUAGE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAttributeLanguage(
            final @RequestParam @Valid @NotEmpty List<Integer> idsAttributes
    ) {
        iDeleteAttributeService.deleteAttribute(idsAttributes);
    }
}
