package com.onsystem.wscapp.pantheon.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsystem.wscapp.pantheon.GenericView;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.UserEntity;
import com.onsystem.wscapp.pantheon.api.request.user.CreateUserRequest;
import com.onsystem.wscapp.pantheon.api.request.user.UpdateUserRequest;
import com.onsystem.wscapp.pantheon.api.response.language.UserResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

import static com.onsystem.wscapp.pantheon.api.request.Constants.ApiUrl.USER_CONTROLLER;

@RestController
@RequestMapping(USER_CONTROLLER)
@Validated
public class UserController {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private IUserService iUserService;


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericView<UserResponse[]> create(final @RequestBody @Valid CreateUserRequest createUserRequest) {
        final UserEntity[] userEntities = objectMapper.convertValue(createUserRequest, UserEntity[].class);
        final List<UserEntity> userSaved = iUserService.create(userEntities);

        final UserResponse[] userResponses = objectMapper.convertValue(userSaved, UserResponse[].class);

        return GenericView.<UserResponse[]>builder()
                .response(userResponses)
                .build();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericView<UserResponse[]> update(final @RequestBody @Valid UpdateUserRequest updateUserRequest) {
        final UserEntity[] userEntities = objectMapper.convertValue(updateUserRequest, UserEntity[].class);
        final List<UserEntity> usersUpdate = iUserService.update(userEntities);

        final UserResponse[] userResponses = objectMapper.convertValue(usersUpdate, UserResponse[].class);

        return GenericView.<UserResponse[]>builder()
                .response(userResponses)
                .build();
    }


    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericView<?> delete(final @RequestParam("ids") Collection<Integer> userIds){

        iUserService.delete(userIds);

        return GenericView.builder()
                .build();
    }


}

