package com.onsystem.wscapp.pantheon.api.interfaces.services.users.update;

import com.onsystem.wscapp.pantheon.api.dto.users.UpdateUserAttributeDTO;

import java.util.Set;

public interface IUpdateUserAttributeService {


    void updateUserAttribute(Set<UpdateUserAttributeDTO> updateUserAttribute);
}
