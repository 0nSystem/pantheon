package com.onsystem.wscapp.pantheon.input.controllers.users;


import com.onsystem.wscapp.pantheon.input.controllers.GenericView;
import com.onsystem.wscapp.pantheon.input.api.dto.users.CreateUserAttributeDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.users.DeleteUserAttributeDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.users.UpdateUserAttributeDTO;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.users.create.ICreateUserAttributeService;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.users.delete.IDeleteUserAttributeService;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.users.update.IUpdateUserAttributeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static com.onsystem.wscapp.pantheon.input.api.interfaces.Constants.ENDPOINT_USER_ATTRIBUTE_ROOT_CONTROLLER;


@RestController
@RequestMapping(ENDPOINT_USER_ATTRIBUTE_ROOT_CONTROLLER)
@Valid
public class UserAttributeController {

    @Autowired
    private ICreateUserAttributeService iCreateUserAttributeService;
    @Autowired
    private IUpdateUserAttributeService iUpdateUserAttributeService;
    @Autowired
    private IDeleteUserAttributeService iDeleteUserAttributeService;


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericView<List<CreateUserAttributeDTO>> assignAttribute(final @RequestBody @Valid List<CreateUserAttributeDTO> createUserAttribute) {
        return GenericView.<List<CreateUserAttributeDTO>>builder()
                .response(iCreateUserAttributeService.assignAttribute(createUserAttribute))
                .build();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateUserAttribute(final @RequestBody @Valid Set<UpdateUserAttributeDTO> updateUserAttribute) {
        iUpdateUserAttributeService.updateUserAttribute(updateUserAttribute);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteUserAttribute(final @RequestBody @Valid Set<DeleteUserAttributeDTO> deleteUserAttribute) {
        iDeleteUserAttributeService.removeAttributesAssigned(deleteUserAttribute);
    }

}
