package com.onsystem.wscapp.pantheon.controllers.users;

import com.onsystem.wscapp.pantheon.api.dto.users.AssingPermissionUser;
import com.onsystem.wscapp.pantheon.api.interfaces.services.users.create.ICreateUserPermissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.onsystem.wscapp.pantheon.api.interfaces.Constants.ENDPOINT_USER_PERMISSION_ROOT_CONTROLLER;

@RestController
@RequestMapping(ENDPOINT_USER_PERMISSION_ROOT_CONTROLLER)
@Valid
public class UserPermissionController {

    @Autowired
    private ICreateUserPermissionService iCreateUserPermissionService;
    //@Autowired
    //TODO private IDeleteUserPermissionService iDeleteUserPermissionService;


    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void assignPermissionInUser(
            final @RequestBody @Valid AssingPermissionUser assingPermissionUser
    ) {
        iCreateUserPermissionService.assignPermissionInUser(assingPermissionUser.getPermissionIds(), assingPermissionUser.getUserId());
    }
}
