package com.onsystem.wscapp.pantheon.input.controllers.users;

import com.onsystem.wscapp.pantheon.input.api.dto.users.AssingRoleUser;
import com.onsystem.wscapp.pantheon.input.api.dto.users.DeleteUserRolesDTO;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.users.create.ICreateUserRoleService;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.users.delete.IDeleteUserRoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.onsystem.wscapp.pantheon.input.api.interfaces.Constants.ENDPOINT_USER_ROLE_ROOT_CONTROLLER;

@RestController
@RequestMapping(ENDPOINT_USER_ROLE_ROOT_CONTROLLER)
@Valid
public class UserRoleController {

    @Autowired
    private ICreateUserRoleService iCreateUserRoleService;
    @Autowired
    private IDeleteUserRoleService iDeleteUserRoleService;


    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void assignRoleInUser(final @RequestBody @Valid AssingRoleUser assingRoleUser) {
        iCreateUserRoleService.assignRole(assingRoleUser.getRoleIds(), assingRoleUser.getUserId());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeRoleInUser(final @RequestBody @Valid Set<DeleteUserRolesDTO> deleteUserRolesDTO) {
        iDeleteUserRoleService.deleteRoles(deleteUserRolesDTO);
    }
}
