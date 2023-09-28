package com.onsystem.wscapp.pantheon.model.service;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.RoleEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.RoleRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<RoleEntity> insert(RoleEntity... roleEntities) {
        //TODO validacion el usuario tiene que estar en la aplicacion

        return roleRepository.saveAll(Arrays.asList(roleEntities));
    }

    @Override
    public List<RoleEntity> update(RoleEntity... roleEntities) {
        //TODO validacion el usuario tiene que estar en la aplicacion

        return roleRepository.saveAll(Arrays.asList(roleEntities));
    }

    @Override
    public List<RoleEntity> findByApplicationId(int applicationId) {
        //TODO validacion el usuario tiene que estar en la aplicacion

        return roleRepository.findByIdApplication(applicationId);
    }
}
