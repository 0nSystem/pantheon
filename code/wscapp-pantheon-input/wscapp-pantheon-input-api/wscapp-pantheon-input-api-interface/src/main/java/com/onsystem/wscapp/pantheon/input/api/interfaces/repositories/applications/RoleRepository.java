package com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications;

import com.onsystem.wscapp.pantheon.input.api.dto.applications.role.UpdateRoleDTO;
import com.onsystem.wscapp.pantheon.commons.entity.applications.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    @Modifying
    @Query("UPDATE RoleEntity SET name = :#{#dto.name}, description = :#{#dto.description} " +
            "WHERE idRole = :#{#dto.idRole}")
    void update(final @Param("dto") UpdateRoleDTO updateRole);

    @Query(" SELECT DISTINCT application.idApplication FROM RoleEntity WHERE idRole IN (:roleIds)")
    Set<Integer> findIdsApplicationByIdRoleIn(Set<Integer> roleIds);

    Set<RoleEntity> findByApplicationIdApplication(final int applicationId);


    Optional<RoleEntity> findByApplicationIdApplicationAndName(final int applicationId,final String roleName);

}
