package com.onsystem.wscapp.pantheon.output.api.interfaces.repositories;

import com.onsystem.wscapp.pantheon.commons.entity.users.UserEntity;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.UserInfoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query(
            "SELECT DISTINCT user " +
                    " FROM UserEntity user " +
                    " INNER JOIN user.role role ON role.name = com.onsystem.wscapp.pantheon.commons.Constants.AUTORIZED_ROLE_NAME" +
                    " WHERE role.application.idApplication = :applicationId " +
                    " AND user.deleteDate IS NULL "
    )
    List<UserInfoProjection> findUserInApplicationByIdApplication(
            final int applicationId
    );


    @Query(
            "SELECT DISTINCT user " +
                    " FROM ApplicationEntity app " +
                    " INNER JOIN app.roles role ON role.name = com.onsystem.wscapp.pantheon.commons.Constants.AUTORIZED_ROLE_NAME " +
                    " INNER JOIN role.user user " +
                    " INNER JOIn user.role user_role " +
                    " WHERE app.idApplication = :applicationId " +
                    " AND user.deleteDate IS NULL " +
                    " AND user_role.idRole IN (:idRoles) "
    )
    List<UserInfoProjection> findUserInApplicationByIdApplicationAndIdRoleInAndDeleteDateIsNull(
            final int applicationId,
            final List<Integer> idRoles
    );


    @Query(
            "SELECT DISTINCT user " +
                    " FROM UserEntity user " +
                    " INNER JOIN user.role role_authorized ON role_authorized.name = com.onsystem.wscapp.pantheon.commons.Constants.AUTORIZED_ROLE_NAME " +
                    " INNER JOIN role_authorized.user user_authorized " +
                    " INNER JOIN user_authorized.permission permission " +
                    " WHERE role_authorized.application.idApplication = :applicationId " +
                    " AND permission.idPermission IN (:idPermission)"
    )
    List<UserInfoProjection> findUserInApplicationByIdApplicationAndIdPermissionInAndDeleteDateIsNull(
            final int applicationId,
            final List<Integer> idPermission
    );

    @Query(
            "SELECT uattr.user " +
                    " FROM ApplicationEntity app " +
                    " INNER JOIN app.roles r ON r.name = com.onsystem.wscapp.pantheon.commons.Constants.AUTORIZED_ROLE_NAME " +
                    " INNER JOIN r.user u ON u.deleteDate IS NULL " +
                    " INNER JOIN u.userAttribute uattr ON uattr.attribute.idAttribute = :attributeId " +
                    " WHERE app.deleteDate IS NULL AND app.idApplication = :applicationId "
    )
    List<UserInfoProjection> findUserInApplicationByIdApplicationAndIdAttributeAndValue(
            final int applicationId,
            final Integer attributeId
    );


    @Query(
            "SELECT DISTINCT u " +
                    " FROM ApplicationEntity app " +
                    " INNER JOIN app.roles r ON r.name = com.onsystem.wscapp.pantheon.commons.Constants.AUTORIZED_ROLE_NAME " +
                    " INNER JOIN r.user u " +
                    " WHERE app.idApplication = :applicationId " +
                    " AND app.deleteDate IS NULL " +
                    " AND u.deleteDate IS NULL "
    )
    List<UserInfoProjection> findUserByIdApplication(
            final int applicationId
    );

}
