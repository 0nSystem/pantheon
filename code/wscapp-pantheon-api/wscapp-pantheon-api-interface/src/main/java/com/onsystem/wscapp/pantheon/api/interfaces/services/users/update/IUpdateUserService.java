package com.onsystem.wscapp.pantheon.api.interfaces.services.users.update;

import com.onsystem.wscapp.pantheon.api.dto.users.UpdateUserDTO;

import java.util.Set;

public interface IUpdateUserService  {


    void updateUser(Set<UpdateUserDTO> updateUser);

}
