package com.onsystem.wscapp.pantheon.input.api.interfaces.services.users.update;

import com.onsystem.wscapp.pantheon.input.api.dto.users.UpdateUserDTO;

import java.util.Set;

public interface IUpdateUserService  {


    void updateUser(Set<UpdateUserDTO> updateUser);

}
