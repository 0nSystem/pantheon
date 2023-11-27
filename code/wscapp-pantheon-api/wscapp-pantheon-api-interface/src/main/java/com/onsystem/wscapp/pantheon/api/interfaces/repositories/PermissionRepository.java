package com.onsystem.wscapp.pantheon.api.interfaces.repositories;

import com.onsystem.wscapp.pantheon.api.dto.permission.UpdatePermissionDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.UpdateRoleLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity,Integer> {

    @Modifying
    @Query("UPDATE PermissionEntity SET name = :#{#dto.name}, description = :#{#dto.description} " +
            "WHERE idPermission = :#{#dto.idPermission}")
    void update(final @Param("dto") UpdatePermissionDTO updatePermission);
}
