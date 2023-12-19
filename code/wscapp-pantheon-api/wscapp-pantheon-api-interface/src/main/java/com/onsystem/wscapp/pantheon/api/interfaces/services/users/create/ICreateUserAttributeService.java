package com.onsystem.wscapp.pantheon.api.interfaces.services.users.create;

import com.onsystem.wscapp.pantheon.api.dto.users.CreateUserAttributeDTO;

import java.util.List;

public interface ICreateUserAttributeService {

    CreateUserAttributeDTO assignAttribute(CreateUserAttributeDTO createUserAttribute);

    List<CreateUserAttributeDTO> assignAttribute(List<CreateUserAttributeDTO> createUserAttribute);
}
