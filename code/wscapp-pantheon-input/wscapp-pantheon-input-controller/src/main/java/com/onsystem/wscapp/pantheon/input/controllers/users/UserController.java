package com.onsystem.wscapp.pantheon.input.controllers.users;

import com.onsystem.wscapp.pantheon.input.controllers.GenericView;
import com.onsystem.wscapp.pantheon.input.api.dto.users.CreateAfterUserDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.users.CreateUserDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.users.UpdateUserDTO;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.users.create.ICreateUserService;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.users.update.IUpdateUserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


import static com.onsystem.wscapp.pantheon.input.api.interfaces.Constants.ENDPOINT_USER_ROOT_CONTROLLER;

@RestController
@RequestMapping(ENDPOINT_USER_ROOT_CONTROLLER)
@Valid
public class UserController {

    @Autowired
    private ICreateUserService iCreateUserService;
    @Autowired
    private IUpdateUserService iUpdateUserService;
    //@Autowired
    //TODO private IDeleteUserService iDeleteUserService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericView<List<CreateAfterUserDTO>> createUsers(
            final @RequestBody @Valid List<CreateUserDTO> createUser
    ) {
        return GenericView.<List<CreateAfterUserDTO>>builder()
                .response(iCreateUserService.createUsers(createUser))
                .build();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateUser(
            final @RequestBody @Valid @NotEmpty Set<UpdateUserDTO> updateUser
    ) {
        iUpdateUserService.updateUser(updateUser);
    }

}
