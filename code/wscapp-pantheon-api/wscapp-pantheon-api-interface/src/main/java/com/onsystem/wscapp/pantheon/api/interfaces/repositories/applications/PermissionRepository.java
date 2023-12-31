package com.onsystem.wscapp.pantheon.api.interfaces.repositories.applications;

import com.onsystem.wscapp.pantheon.api.dto.applications.permission.UpdatePermissionDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity,Integer> {

    @Modifying
    @Query("UPDATE PermissionEntity SET name = :#{#dto.name}, description = :#{#dto.description} " +
            "WHERE idPermission = :#{#dto.idPermission}")
    void update(final @Param("dto") UpdatePermissionDTO updatePermission);

    List<PermissionEntity> findByApplicationIdApplication(final Integer idApplication);
}
