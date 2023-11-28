package com.onsystem.wscapp.pantheon.api.interfaces.repositories;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.RolePermissionEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.RolePermissionKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermissionEntity, RolePermissionKeyEntity> {


    @Modifying
    void deleteByIdRoleIn(Collection<Integer> roleIds);

    @Modifying
    void deleteByIdPermissionIn(Collection<Integer> permissionIds);
}
