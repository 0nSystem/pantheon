package com.onsystem.wscapp.pantheon.api.interfaces.repositories.users;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.RoleEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserRoleEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserRoleKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, UserRoleKeyEntity> {


    @Query("SELECT DISTINCT role FROM UserRoleEntity WHERE role.idRole IN (:roleIds) AND user.idUser IN (:userId)")
    Set<RoleEntity> findByUserRoleEntitiesByIdRoleInAndIdUser(Set<Integer> roleIds, int userId);

    @Query(" SELECT DISTINCT role.application.idApplication " +
            " FROM UserRoleEntity WHERE user.idUser = :userId AND role.name = :authorizedPermission")
    Set<Integer> findIdsApplicationByUser(final Integer userId, final String authorizedPermission);


    @Query(" SELECT role FROM UserRoleEntity WHERE user.idUser = :userId AND role.application.idApplication = :applicationId")
    Set<RoleEntity> findRoleEntitiesByIdUserAndIdApplication(
            final int userId,
            final int applicationId
    );

}
