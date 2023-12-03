package com.onsystem.wscapp.pantheon.api.interfaces.repositories.applications;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.RolePermissionEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.RolePermissionKeyEntity;
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
