package com.onsystem.wscapp.pantheon.api.interfaces.repositories.users;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserPermissionEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserPermissionKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPermissionRepository extends JpaRepository<UserPermissionEntity, UserPermissionKeyEntity> {
}
