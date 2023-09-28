package com.onsystem.wscapp.pantheon.controller;

import com.onsystem.wscapp.pantheon.GenericView;
import com.onsystem.wscapp.pantheon.api.interfaces.services.IUserPermissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.onsystem.wscapp.pantheon.api.request.Constants.ApiUrl.USER_PERMISSION_CONTROLLER;

@RestController
@RequestMapping(USER_PERMISSION_CONTROLLER)
@Validated
public class UserPermissionController {

    @Autowired
    private IUserPermissionService iUserPermissionService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericView<?> create(final @RequestBody @Valid Set<Integer> idsPermissions,
                                 final @RequestBody @Valid Set<Integer> idsUsers) {

        iUserPermissionService.insert(idsPermissions, idsUsers);
        return GenericView.builder()
                .build();
    }


    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericView<?> update(final @RequestBody @Valid Set<Integer> idsPermissions,
                                 final @RequestBody @Valid Set<Integer> idsUsers) {

        iUserPermissionService.delete(idsPermissions, idsUsers);
        return GenericView.builder()
                .build();
    }
}
