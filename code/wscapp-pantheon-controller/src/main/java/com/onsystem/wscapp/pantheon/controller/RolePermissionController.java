package com.onsystem.wscapp.pantheon.controller;

import com.onsystem.wscapp.pantheon.GenericView;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.onsystem.wscapp.pantheon.api.request.Constants.ApiUrl.ROLE_PERMISSION_CONTROLLER;

@RestController
@RequestMapping(ROLE_PERMISSION_CONTROLLER)
@Validated
public class RolePermissionController {

    @Autowired
    private IRolePermissionService iRolePermissionService;


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericView<?> create(final @RequestBody @Valid @NotEmpty Set<Integer> idsRoles,
                                 final @RequestBody @Valid @NotEmpty Set<Integer> idsPermissions) {

        iRolePermissionService.insert(idsRoles, idsPermissions);
        return GenericView.builder()
                .build();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericView<?> delete(final @RequestBody @Valid @NotEmpty Set<Integer> idsRoles,
                                 final @RequestBody @Valid @NotEmpty Set<Integer> idsPermissions) {

        iRolePermissionService.delete(idsRoles, idsPermissions);
        return GenericView.builder()
                .build();
    }
}
