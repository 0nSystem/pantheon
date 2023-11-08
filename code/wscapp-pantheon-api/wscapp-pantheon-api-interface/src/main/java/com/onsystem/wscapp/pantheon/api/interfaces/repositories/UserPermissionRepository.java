package com.onsystem.wscapp.pantheon.api.interfaces.repositories;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.UserPermissionEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.UserPermissionKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPermissionRepository extends JpaRepository<UserPermissionEntity, UserPermissionKeyEntity> {
}
