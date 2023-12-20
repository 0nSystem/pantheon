package com.onsystem.wscapp.pantheon.api.interfaces.repositories.applications;

import com.onsystem.wscapp.pantheon.api.dto.applications.role.UpdateRoleDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    @Modifying
    @Query("UPDATE RoleEntity SET name = :#{#dto.name}, description = :#{#dto.description} " +
            "WHERE idRole = :#{#dto.idRole}")
    void update(final @Param("dto") UpdateRoleDTO updateRole);

    @Query(" SELECT application.idApplication FROM RoleEntity WHERE idRole IN (:roleIds)")
    List<Integer> findIdsApplicationByIdRoleIn(List<Integer> roleIds);

}
