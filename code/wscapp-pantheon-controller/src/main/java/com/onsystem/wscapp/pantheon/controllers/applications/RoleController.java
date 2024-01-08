package com.onsystem.wscapp.pantheon.controllers.applications;

import com.onsystem.wscapp.pantheon.GenericView;
import com.onsystem.wscapp.pantheon.api.dto.applications.role.*;
import com.onsystem.wscapp.pantheon.api.interfaces.services.applications.create.ICreateRoleService;
import com.onsystem.wscapp.pantheon.api.interfaces.services.applications.delete.IDeleteRoleService;
import com.onsystem.wscapp.pantheon.api.interfaces.services.applications.update.IUpdateRoleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static com.onsystem.wscapp.pantheon.api.interfaces.Constants.ENDPOINT_ROLE_ROOT_CONTROLLER;
import static com.onsystem.wscapp.pantheon.api.interfaces.Constants.ENDPOINT_TO_LANGUAGE;

@RestController
@RequestMapping(ENDPOINT_ROLE_ROOT_CONTROLLER)
@Validated
public class RoleController {


    @Autowired
    private ICreateRoleService iCreateRoleService;
    @Autowired
    private IUpdateRoleService iUpdateRoleService;
    @Autowired
    private IDeleteRoleService iDeleteRoleService;


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericView<Set<RoleDTO>> createRole(@RequestParam @Valid @Positive Integer applicationId,
                                                @RequestBody @Valid Set<CreateRoleDTO> createRole) {
        return GenericView.<Set<RoleDTO>>builder()
                .response(iCreateRoleService.createRole(applicationId, createRole))
                .build();
    }

    @PostMapping(ENDPOINT_TO_LANGUAGE)
    @ResponseStatus(HttpStatus.OK)
    public GenericView<Set<RoleLanguageDTO>> createRoleLanguage(@RequestParam @Valid @Positive Integer idRole,
                                                                @RequestBody @Valid Set<CreateRoleLanguageDTO> createRoleLanguage) {
        return GenericView.<Set<RoleLanguageDTO>>builder()
                .response(iCreateRoleService.createRoleLanguages(idRole, createRoleLanguage))
                .build();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateRole(@RequestBody @Valid final Set<UpdateRoleDTO> updateRole) {
        iUpdateRoleService.updateRoles(updateRole);
    }

    @PutMapping(ENDPOINT_TO_LANGUAGE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateRoleLanguage(@RequestBody @Valid final Set<UpdateRoleLanguageDTO> updateRoleLanguage) {
        iUpdateRoleService.updateRoleLanguages(updateRoleLanguage);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteRole(final @RequestBody @Valid @Positive List<Integer> idsRoles) {
        iDeleteRoleService.deleteRole(idsRoles);
    }

    @DeleteMapping(ENDPOINT_TO_LANGUAGE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteRoleLanguage(
            final @RequestParam @Valid @Positive Integer roleId,
            final @RequestBody @Valid @Positive List<Integer> languagesIds) {
        iDeleteRoleService.deleteRoleLanguage(roleId, languagesIds);
    }


}
