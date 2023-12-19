package com.onsystem.wscapp.pantheon.api.interfaces.repositories.users;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserRoleEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserRoleKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, UserRoleKeyEntity> {


    @Query("SELECT COUNT(*) FROM UserRoleEntity WHERE role.idRole IN (:roleIds) AND user.idUser IN (:userId)")
    int countUserRoleEntitiesByIdRoleInAndIdUser(List<Integer> roleIds, int userId);

    @Query(" SELECT role.application.idApplication " +
            " FROM UserRoleEntity WHERE user.idUser = :userId AND role.name = :authorizedPermission")
    List<Integer> findIdsApplicationByUser(final Integer userId, final String authorizedPermission);

}
