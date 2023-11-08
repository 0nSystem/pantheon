package com.onsystem.wscapp.pantheon.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsystem.wscapp.pantheon.GenericView;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.AttributeEntity;
import com.onsystem.wscapp.pantheon.api.request.attribute.CreateAttributeRequest;
import com.onsystem.wscapp.pantheon.api.request.attribute.UpdateAttributeRequest;
import com.onsystem.wscapp.pantheon.api.response.language.AttributeResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.onsystem.wscapp.pantheon.api.request.Constants.ApiUrl.ATTRIBUTE_CONTROLLER;

@RestController
@Validated
@RequestMapping(ATTRIBUTE_CONTROLLER)
public class AttributeController {

    @Autowired
    private IAttributeService iAttributeService;

    @Autowired
    private ObjectMapper objectMapper;


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericView<AttributeResponse[]> create(final @Valid @RequestBody CreateAttributeRequest[] createPermissionRequests) {
        final AttributeEntity[] attributeEntities = objectMapper.convertValue(createPermissionRequests, AttributeEntity[].class);
        final List<AttributeEntity> attributeEntitiesSaved = iAttributeService.insert(attributeEntities);
        final AttributeResponse[] permissionsResponse = objectMapper.convertValue(attributeEntitiesSaved, AttributeResponse[].class);

        return GenericView.<AttributeResponse[]>builder()
                .response(permissionsResponse)
                .build();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericView<AttributeResponse[]> update(final @Valid @RequestBody UpdateAttributeRequest[] updatePermissionRequests) {
        final AttributeEntity[] attributeEntities = objectMapper.convertValue(updatePermissionRequests, AttributeEntity[].class);
        final List<AttributeEntity> attributeEntitiesSaved = iAttributeService.update(attributeEntities);
        final AttributeResponse[] attributeEntitiesRespose = objectMapper.convertValue(attributeEntitiesSaved, AttributeResponse[].class);

        return GenericView.<AttributeResponse[]>builder()
                .response(attributeEntitiesRespose)
                .build();
    }

    @GetMapping("/findByApplicationId/{applicationId}")
    @ResponseStatus(HttpStatus.OK)
    public GenericView<AttributeResponse[]> findByApplicationId(final @PathVariable @Valid @Positive int applicationId) {
        final List<AttributeEntity> attributeEntities = iAttributeService.findByApplicationId(applicationId);
        final AttributeResponse[] attributeResponses = objectMapper.convertValue(attributeEntities, AttributeResponse[].class);

        return GenericView.<AttributeResponse[]>builder()
                .response(attributeResponses)
                .build();
    }

}
