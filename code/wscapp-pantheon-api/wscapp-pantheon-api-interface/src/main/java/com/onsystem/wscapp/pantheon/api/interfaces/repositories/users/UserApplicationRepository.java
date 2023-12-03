package com.onsystem.wscapp.pantheon.api.interfaces.repositories.users;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserApplicationEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserApplicationKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserApplicationRepository extends JpaRepository<UserApplicationEntity, UserApplicationKeyEntity> {
}
