package com.onsystem.wscapp.pantheon.controllers.applications;

import com.onsystem.wscapp.pantheon.GenericView;
import com.onsystem.wscapp.pantheon.api.dto.applications.application.*;
import com.onsystem.wscapp.pantheon.api.interfaces.services.applications.create.ICreateApplicationService;
import com.onsystem.wscapp.pantheon.api.interfaces.services.applications.update.IUpdateApplicationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static com.onsystem.wscapp.pantheon.api.interfaces.Constants.ENDPOINT_APPLICATION_ROOT_CONTROLLER;
import static com.onsystem.wscapp.pantheon.api.interfaces.Constants.ENDPOINT_TO_LANGUAGE;

@RestController
@RequestMapping(ENDPOINT_APPLICATION_ROOT_CONTROLLER)
@Validated
public class ApplicationController {

    @Autowired
    private ICreateApplicationService iCreateApplicationService;
    @Autowired
    private IUpdateApplicationService iUpdateApplicationService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericView<ApplicationDTO> createApplication(@RequestBody @Valid CreateApplicationDTO createApplication) {
        return GenericView.<ApplicationDTO>builder()
                .response(iCreateApplicationService.createApplication(createApplication))
                .build();
    }

    @PostMapping(ENDPOINT_TO_LANGUAGE)
    @ResponseStatus(HttpStatus.OK)
    public GenericView<Set<ApplicationLanguageDTO>> createLanguageApplication(
            @RequestParam @Positive Integer applicationId,
            @RequestBody @Valid Set<CreateApplicationLanguageDTO> createApplicationLanguage) {

        return GenericView.<Set<ApplicationLanguageDTO>>builder()
                .response(iCreateApplicationService.createApplicationLanguages(applicationId, createApplicationLanguage))
                .build();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateApplication(@RequestBody @Valid UpdateApplicationDTO updateApplicationDTO) {
        iUpdateApplicationService.updateApplication(List.of(updateApplicationDTO));
    }

    @PutMapping(ENDPOINT_TO_LANGUAGE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateApplicationLanguage(@RequestBody @Valid Set<UpdateApplicationLanguageDTO> updateApplicationLanguageDTO) {
        iUpdateApplicationService.updateApplicationLanguages(updateApplicationLanguageDTO);
    }


}