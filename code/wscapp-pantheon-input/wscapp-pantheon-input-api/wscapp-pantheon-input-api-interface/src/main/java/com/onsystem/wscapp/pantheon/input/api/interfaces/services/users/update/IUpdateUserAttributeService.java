package com.onsystem.wscapp.pantheon.input.api.interfaces.services.users.update;

import com.onsystem.wscapp.pantheon.input.api.dto.users.UpdateUserAttributeDTO;

import java.util.Set;

public interface IUpdateUserAttributeService {


    void updateUserAttribute(Set<UpdateUserAttributeDTO> updateUserAttribute);
}
