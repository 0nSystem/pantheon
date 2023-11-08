package com.onsystem.wscapp.pantheon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsystem.wscapp.pantheon.GenericView;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.LanguageEntity;
import com.onsystem.wscapp.pantheon.api.request.language.CreateLanguageRequest;
import com.onsystem.wscapp.pantheon.api.request.language.UpdateLanguageRequest;
import com.onsystem.wscapp.pantheon.api.response.language.LanguageResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.onsystem.wscapp.pantheon.api.request.Constants.ApiUrl.LANGUAGE_CONTROLLER;

@RestController
@RequestMapping(LANGUAGE_CONTROLLER)
@Validated
public class LanguageController {
    //Require permission to create language to global context, or update
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ILanguageService iLanguageService;


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericView<LanguageResponse[]> create(final @Valid @RequestBody List<CreateLanguageRequest> createLanguages) {
        final LanguageEntity[] languageEntitiesParsed = objectMapper.convertValue(createLanguages,LanguageEntity[].class);

        final List<LanguageEntity> languageEntities = iLanguageService.create(languageEntitiesParsed);
        final LanguageResponse[] languageResponses = objectMapper.convertValue(languageEntities, LanguageResponse[].class);

        return GenericView.<LanguageResponse[]>builder()
                .response(languageResponses)
                .build();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericView<LanguageResponse[]> update(final @Valid @RequestBody List<UpdateLanguageRequest> updateLanguages) {

        final LanguageEntity[] languageEntitiesParsed = objectMapper.convertValue(updateLanguages,LanguageEntity[].class);

        final List<LanguageEntity> languageEntities = iLanguageService.update(languageEntitiesParsed);
        final LanguageResponse[] languageResponses = objectMapper.convertValue(languageEntities, LanguageResponse[].class);

        return GenericView.<LanguageResponse[]>builder()
                .response(languageResponses)
                .build();
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericView<LanguageResponse[]> get() {
        final List<LanguageEntity> languages = iLanguageService.findAll();
        final LanguageResponse[] languagesResponse = objectMapper.convertValue(languages, LanguageResponse[].class);

        return GenericView.<LanguageResponse[]>builder()
                .response(languagesResponse)
                .build();
    }

    @GetMapping("/{idLanguage}")
    @ResponseStatus(HttpStatus.OK)
    public GenericView<LanguageResponse> get(final @PathVariable int idLanguage) {
        final LanguageEntity language = iLanguageService.findById(idLanguage);
        final LanguageResponse languageResponse = objectMapper.convertValue(language, LanguageResponse.class);

        return GenericView.<LanguageResponse>builder()
                .response(languageResponse)
                .build();
    }
}
