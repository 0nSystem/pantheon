package com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.users;

import com.onsystem.wscapp.pantheon.commons.entity.applications.RoleEntity;
import com.onsystem.wscapp.pantheon.commons.entity.users.UserRoleEntity;
import com.onsystem.wscapp.pantheon.commons.entity.users.UserRoleKeyEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.projections.UserBelongApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, UserRoleKeyEntity> {


    @Query("SELECT DISTINCT role FROM UserRoleEntity WHERE role.idRole IN (:roleIds) AND user.idUser IN (:userId)")
    Set<RoleEntity> findByUserRoleEntitiesByIdRoleInAndIdUser(Set<Integer> roleIds, int userId);

    @Query(" SELECT DISTINCT " +
            " user.idUser AS idUser," +
            " role.application.idApplication AS idApplication " +
            " FROM UserRoleEntity " +
            " WHERE role.application IS NOT NULL " +
            " AND user.idUser IN (:userId) " +
            " AND role.name = :authorizedPermission")
    Set<UserBelongApplication> findIdsApplicationByUser(
            final Set<Integer> userId, final String authorizedPermission);


    @Query(" SELECT role FROM UserRoleEntity WHERE user.idUser = :userId AND role.application.idApplication = :applicationId")
    Set<RoleEntity> findRoleEntitiesByIdUserAndIdApplication(
            final int userId,
            final int applicationId
    );

}
