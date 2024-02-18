package com.onsystem.wscapp.pantheon.input.api.interfaces.services.users.create;

import com.onsystem.wscapp.pantheon.input.api.dto.users.CreateUserAttributeDTO;

import java.util.List;

public interface ICreateUserAttributeService {


    List<CreateUserAttributeDTO> assignAttribute(List<CreateUserAttributeDTO> createUserAttribute);
}
