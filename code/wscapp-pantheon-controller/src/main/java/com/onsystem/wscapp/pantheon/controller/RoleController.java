package com.onsystem.wscapp.pantheon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsystem.wscapp.pantheon.GenericView;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.RoleEntity;
import com.onsystem.wscapp.pantheon.api.request.role.CreateRoleRequest;
import com.onsystem.wscapp.pantheon.api.request.role.UpdateRoleRequest;
import com.onsystem.wscapp.pantheon.api.response.language.RoleResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.onsystem.wscapp.pantheon.api.request.Constants.ApiUrl.ROLE_CONTROLLER;

@Validated
@RestController
@RequestMapping(ROLE_CONTROLLER)
public class RoleController {


    @Autowired
    private IRoleService iRoleService;
    @Autowired
    private ObjectMapper objectMapper;


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericView<RoleResponse[]> create(final @RequestBody @Valid CreateRoleRequest[] updateRoleRequest) {
        final RoleEntity[] rolesEntity = objectMapper.convertValue(updateRoleRequest,RoleEntity[].class);
        final List<RoleEntity> rolesEntitySaved = iRoleService.insert(rolesEntity);
        final RoleResponse[] rolesResponse = objectMapper.convertValue(rolesEntitySaved,RoleResponse[].class);

        return GenericView.<RoleResponse[]>builder()
                .response(rolesResponse)
                .build();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericView<RoleResponse[]> update(final @RequestBody @Valid UpdateRoleRequest[] updateRoleRequest) {
        final RoleEntity[] roleEntities = objectMapper.convertValue(updateRoleRequest, RoleEntity[].class);
        final List<RoleEntity> roleEntitiesSaved = iRoleService.update(roleEntities);
        final RoleResponse[] rolesResponse = objectMapper.convertValue(roleEntitiesSaved, RoleResponse[].class);

        return GenericView.<RoleResponse[]>builder()
                .response(rolesResponse)
                .build();
    }

    @GetMapping("/findByApplicationName")
    @ResponseStatus(HttpStatus.OK)
    public GenericView<RoleResponse[]> findByApplicationName(final @PathVariable @Validated @Positive Integer applicationId){
        final List<RoleEntity> roleEntities = iRoleService.findByApplicationId(applicationId);
        final RoleResponse[] rolesResponse = objectMapper.convertValue(roleEntities, RoleResponse[].class);

        return GenericView.<RoleResponse[]>builder()
                .response(rolesResponse)
                .build();
    }
}
