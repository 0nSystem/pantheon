package com.onsystem.wscapp.pantheon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsystem.wscapp.pantheon.GenericView;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.services.IApplicationService;
import com.onsystem.wscapp.pantheon.api.request.application.CreateApplicationRequest;
import com.onsystem.wscapp.pantheon.api.request.application.UpdateApplicationRequest;
import com.onsystem.wscapp.pantheon.api.response.language.ApplicationResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.onsystem.wscapp.pantheon.api.request.Constants.ApiUrl.APPLICATION_CONTROLLER;


@RestController
@RequestMapping(APPLICATION_CONTROLLER)
@Validated
public class ApplicationController {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private IApplicationService iApplicationService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericView<ApplicationResponse[]> create(final @Valid @RequestBody CreateApplicationRequest createApplicationRequest){
        final ApplicationEntity applicationEntity = objectMapper.convertValue(createApplicationRequest,ApplicationEntity.class);
        final List<ApplicationEntity> applicationEntitySaved = iApplicationService.create(applicationEntity);
        final ApplicationResponse[] applicationResponses = objectMapper.convertValue(applicationEntitySaved,ApplicationResponse[].class);

        return GenericView.<ApplicationResponse[]>builder()
                .response(applicationResponses)
                .build();
    }


    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericView<Object> update(final @Valid @RequestBody UpdateApplicationRequest updateApplicationRequest){
        final ApplicationEntity applicationEntity = objectMapper.convertValue(updateApplicationRequest,ApplicationEntity.class);
        iApplicationService.update(applicationEntity);

        return GenericView.builder()
                .build();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericView<Object> delete(final @RequestParam @Valid @PositiveOrZero Integer id){
        iApplicationService.delete(id);

        return GenericView.builder()
                .build();
    }


}
