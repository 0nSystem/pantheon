package com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications;

import com.onsystem.wscapp.pantheon.input.api.interfaces.entity.applications.RolePermissionEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.entity.applications.RolePermissionKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermissionEntity, RolePermissionKeyEntity> {


    @Modifying
    @Query("DELETE FROM RolePermissionEntity WHERE role.idRole IN (:roleIds)")
    void deleteByIdRoleIn(Collection<Integer> roleIds);

    @Modifying
    @Query("DELETE FROM RolePermissionEntity WHERE permission.idPermission IN (:permissionIds)")
    void deleteByIdPermissionIn(Collection<Integer> permissionIds);
}
