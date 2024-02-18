package com.onsystem.wscapp.pantheon.input.api.interfaces.services.users.delete;

import com.onsystem.wscapp.pantheon.input.api.dto.users.DeleteUserAttributeDTO;

import java.util.Set;

public interface IDeleteUserAttributeService {


    void removeAttributesAssigned(final Set<DeleteUserAttributeDTO> deleteUserAttributes);
}
