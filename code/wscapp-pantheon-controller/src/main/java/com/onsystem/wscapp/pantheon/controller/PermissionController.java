package com.onsystem.wscapp.pantheon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsystem.wscapp.pantheon.GenericView;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.PermissionEntity;
import com.onsystem.wscapp.pantheon.api.request.permission.CreatePermissionRequest;
import com.onsystem.wscapp.pantheon.api.request.permission.UpdatePermissionRequest;
import com.onsystem.wscapp.pantheon.api.response.language.PermissionResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.onsystem.wscapp.pantheon.api.request.Constants.ApiUrl.PERMISSION_CONTROLLER;

@RestController
@Validated
@RequestMapping(PERMISSION_CONTROLLER)
public class PermissionController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IPermissionService iPermissionService;



    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericView<PermissionResponse[]> create(final @Valid @RequestBody CreatePermissionRequest[] createPermissionRequests){
        final PermissionEntity[] permissionEntities = objectMapper.convertValue(createPermissionRequests, PermissionEntity[].class);
        final List<PermissionEntity> permissionEntitiesSaved = iPermissionService.create(permissionEntities);
        final PermissionResponse[] permissionsResponse = objectMapper.convertValue(permissionEntitiesSaved, PermissionResponse[].class);

        return GenericView.<PermissionResponse[]>builder()
                .response(permissionsResponse)
                .build();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericView<PermissionResponse[]> update(final @Valid @RequestBody UpdatePermissionRequest[] updatePermissionRequests){
        final PermissionEntity[] permissionEntities = objectMapper.convertValue(updatePermissionRequests, PermissionEntity[].class);
        final List<PermissionEntity> permissionEntitiesSaved = iPermissionService.update(permissionEntities);
        final PermissionResponse[] permissionsResponse = objectMapper.convertValue(permissionEntitiesSaved, PermissionResponse[].class);

        return GenericView.<PermissionResponse[]>builder()
                .response(permissionsResponse)
                .build();
    }

    @GetMapping("/findByApplicationId/{applicationId}")
    @ResponseStatus(HttpStatus.OK)
    public GenericView<PermissionResponse[]> findByApplicationId(final @PathVariable @Valid @Positive int applicationId){
        final List<PermissionEntity> permissionEntitiesSaved = iPermissionService.findByApplicationId(applicationId);
        final PermissionResponse[] permissionsResponse = objectMapper.convertValue(permissionEntitiesSaved, PermissionResponse[].class);

        return GenericView.<PermissionResponse[]>builder()
                .response(permissionsResponse)
                .build();
    }




}
