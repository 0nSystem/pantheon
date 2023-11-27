package com.onsystem.wscapp.pantheon.api.interfaces.repositories;

import com.onsystem.wscapp.pantheon.api.dto.role.UpdateRoleDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    @Modifying
    @Query("UPDATE RoleEntity SET name = :#{#dto.name}, description = :#{#dto.description} " +
            "WHERE idRole = :#{#dto.idRole}")
    void update(final @Param("dto") UpdateRoleDTO updateRole);

}
