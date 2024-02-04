package com.onsystem.wscapp.pantheon.input.controllers.applications;

import com.onsystem.wscapp.pantheon.input.controllers.GenericView;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.permission.*;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.applications.create.ICreatePermissionService;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.applications.delete.IDeletePermissionService;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.applications.update.IUpdatePermissionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;



import static com.onsystem.wscapp.pantheon.input.api.interfaces.Constants.ENDPOINT_PERMISSION_ROOT_CONTROLLER;
import static com.onsystem.wscapp.pantheon.input.api.interfaces.Constants.ENDPOINT_TO_LANGUAGE;

@RestController
@RequestMapping(ENDPOINT_PERMISSION_ROOT_CONTROLLER)
@Validated
public class PermissionController {

    @Autowired
    private ICreatePermissionService iCreatePermissionService;
    @Autowired
    private IUpdatePermissionService iUpdatePermissionService;
    @Autowired
    private IDeletePermissionService iDeletePermissionService;


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericView<Set<PermissionWithLanguagesDTO>> createPermission(@RequestParam @Valid @Positive Integer applicationId,
                                                                         @RequestParam(required = false) @Valid Integer roleId,
                                                                         @RequestBody @Valid @NotEmpty Set<CreatePermissionWithLanguagesDTO> createPermission) {
        return GenericView.<Set<PermissionWithLanguagesDTO>>builder()
                .response(iCreatePermissionService.createPermissionWithLanguages(applicationId, roleId, createPermission))
                .build();
    }

    @PostMapping(ENDPOINT_TO_LANGUAGE)
    @ResponseStatus(HttpStatus.OK)
    public GenericView<Set<PermissionDTO>> createPermissionLanguage(@RequestParam @Valid @Positive Integer applicationId,
                                                                    @RequestParam(required = false) @Valid Integer roleId,
                                                                    @RequestBody @Valid @NotEmpty Set<CreatePermissionDTO> createPermission) {
        return GenericView.<Set<PermissionDTO>>builder()
                .response(iCreatePermissionService.createPermission(applicationId, roleId, createPermission))
                .build();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updatePermission(final @RequestBody @Valid @NotEmpty Set<UpdatePermissionDTO> updatePermission) {
        iUpdatePermissionService.updatePermission(updatePermission);
    }

    @PutMapping(ENDPOINT_TO_LANGUAGE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updatePermissionLanguages(final @RequestBody @Valid @NotEmpty Set<UpdatePermissionLanguageDTO> updatePermissionLanguages) {
        iUpdatePermissionService.updatePermissionLanguages(updatePermissionLanguages);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deletePermission(@RequestParam @Valid @NotEmpty Set<Integer> permissionIds) {
        iDeletePermissionService.deletePermission(permissionIds);
    }

    @DeleteMapping(ENDPOINT_TO_LANGUAGE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deletePermissionLanguages(@RequestParam @Valid @Positive Integer permissionId,
                                          @RequestParam @Valid @NotEmpty @Positive Collection<Integer> languageIds) {
        iDeletePermissionService.deletePermissionLanguage(permissionId, languageIds);
    }


}
