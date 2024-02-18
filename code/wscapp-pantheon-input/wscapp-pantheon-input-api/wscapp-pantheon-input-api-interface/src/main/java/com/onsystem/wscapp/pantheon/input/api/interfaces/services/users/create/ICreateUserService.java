package com.onsystem.wscapp.pantheon.input.api.interfaces.services.users.create;

import com.onsystem.wscapp.pantheon.input.api.dto.users.CreateAfterUserDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.users.CreateUserDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface ICreateUserService {
    @NotNull CreateAfterUserDTO createUser(@NotNull CreateUserDTO createUser);

    @NotEmpty List<CreateAfterUserDTO> createUsers(@NotEmpty List<CreateUserDTO> createUser);

}
