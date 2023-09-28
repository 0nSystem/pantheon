package com.onsystem.wscapp.pantheon.model.service;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.UserApplicationEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.UserApplicationRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.IUserApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserApplicationService implements IUserApplicationService {
    @Autowired
    private UserApplicationRepository userApplicationRepository;

    @Override
    public void insert(Collection<Integer> idsApplications, Collection<Integer> idsUsers) {
        //TODO El usuario que lo crea tiene que estar en la aplicacion

        final List<UserApplicationEntity> applicationEntities = idsApplications.stream()
                .flatMap(idApplication -> idsUsers.stream().map(
                        idUser -> UserApplicationEntity.builder().idApplication(idApplication).idUser(idUser).build())
                ).toList();

        userApplicationRepository.saveAll(applicationEntities);
    }

    @Override
    public void delete(Collection<Integer> idsApplications, Collection<Integer> idsUsers) {
        //TODO El usuario que lo crea tiene que estar en la aplicacion

        final List<UserApplicationEntity> applicationEntities = idsApplications.stream()
                .flatMap(idApplication -> idsUsers.stream().map(
                        idUser -> UserApplicationEntity.builder().idApplication(idApplication).idUser(idUser).build())
                ).toList();

        userApplicationRepository.saveAll(applicationEntities);
    }
}
