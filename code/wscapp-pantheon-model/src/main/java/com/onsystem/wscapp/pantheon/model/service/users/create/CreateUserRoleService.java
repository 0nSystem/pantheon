package com.onsystem.wscapp.pantheon.model.service.users.create;

import com.onsystem.wscapp.pantheon.api.interfaces.services.users.create.ICreateUserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateUserRoleService implements ICreateUserRoleService {
    @Override
    public void assignRole(List<Integer> roleIds, int userId) {

    }
}
