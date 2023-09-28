package com.onsystem.wscapp.pantheon.api.interfaces.services;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.UserEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.List;

@Validated
public interface IUserService {

    List<UserEntity> create(UserEntity... userEntities);
    List<UserEntity> update(UserEntity... userEntities);
    void delete(final Collection<Integer> userId);
    UserEntity findById(final @Valid @Positive int id);

}
