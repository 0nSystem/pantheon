package com.onsystem.wscapp.pantheon.api.interfaces.services.users.delete;

import com.onsystem.wscapp.pantheon.api.dto.users.DeleteUserAttributeDTO;

import java.util.Set;

public interface IDeleteUserAttributeService {


    void removeAttributesAssigned(final Set<DeleteUserAttributeDTO> deleteUserAttributes);
}
