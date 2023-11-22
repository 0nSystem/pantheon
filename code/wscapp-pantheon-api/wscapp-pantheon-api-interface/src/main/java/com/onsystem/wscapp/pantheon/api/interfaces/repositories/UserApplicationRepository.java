package com.onsystem.wscapp.pantheon.api.interfaces.repositories;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.UserApplicationEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.UserApplicationKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserApplicationRepository extends JpaRepository<UserApplicationEntity, UserApplicationKeyEntity> {
}
