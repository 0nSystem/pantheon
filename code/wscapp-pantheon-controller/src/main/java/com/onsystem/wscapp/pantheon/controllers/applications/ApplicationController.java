package com.onsystem.wscapp.pantheon.controllers.applications;

import com.onsystem.wscapp.pantheon.GenericView;
import com.onsystem.wscapp.pantheon.api.dto.applications.application.*;
import com.onsystem.wscapp.pantheon.api.interfaces.services.applications.create.ICreateApplicationService;
import com.onsystem.wscapp.pantheon.api.interfaces.services.applications.update.IUpdateApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static com.onsystem.wscapp.pantheon.api.interfaces.Constants.ENDPOINT_APPLICATION_ROOT_CONTROLLER;
import static com.onsystem.wscapp.pantheon.api.interfaces.Constants.ENDPOINT_TO_LANGUAGE;

@RestController(ENDPOINT_APPLICATION_ROOT_CONTROLLER)
public class ApplicationController {

    @Autowired
    private ICreateApplicationService iCreateApplicationService;
    @Autowired
    private IUpdateApplicationService iUpdateApplicationService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericView<ApplicationDTO> createApplication(@RequestBody CreateApplicationDTO createApplication) {
        return GenericView.<ApplicationDTO>builder()
                .response(iCreateApplicationService.createApplication(createApplication))
                .build();
    }

    @PostMapping(ENDPOINT_TO_LANGUAGE)
    @ResponseStatus(HttpStatus.OK)
    public GenericView<?> createLanguageApplication(
            @RequestParam Integer applicationId,
            @RequestBody Set<CreateApplicationLanguageDTO> createApplicationLanguage) {

        return GenericView.builder()
                .response(iCreateApplicationService.createApplicationLanguages(applicationId, createApplicationLanguage))
                .build();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public GenericView<?> updateApplication(@RequestBody UpdateApplicationDTO updateApplicationDTO) {
        iUpdateApplicationService.updateApplication(List.of(updateApplicationDTO));
        return GenericView.builder()
                .build();
    }

    @PutMapping(ENDPOINT_TO_LANGUAGE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public GenericView<?> updateApplicationLanguage(@RequestBody Set<UpdateApplicationLanguageDTO> updateApplicationLanguageDTO) {
        iUpdateApplicationService.updateApplicationLanguages(updateApplicationLanguageDTO);
        return GenericView.builder()
                .build();
    }


}
