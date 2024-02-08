package com.onsystem.wscapp.pantheon.output.api.interfaces.repositories;

import com.onsystem.wscapp.pantheon.commons.entity.users.UserEntity;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.AttributeUserDataProjection;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.UserInfoProjection;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.UserPermissionInfoProjection;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.UserRoleInfoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {


    @Query(
            "SELECT DISTINCT role.application.idApplication AS idApplication," +
                    " user.idUser AS idUser," +
                    " user.name AS name, " +
                    " user.surname AS surname, " +
                    " user.login as login, " +
                    " user.email as email, " +
                    " user.highDate as highDate," +
                    " user.highIdUser as highIdUser " +
                    " FROM UserEntity user " +
                    " INNER JOIN user.role role ON role.name = com.onsystem.wscapp.pantheon.commons.Constants.AUTORIZED_ROLE_NAME" +
                    " WHERE role.application.idApplication IN (:applicationId) " +
                    " AND user.deleteDate IS NULL "
    )
    List<UserInfoProjection> findUserInApplicationByIdApplication(
            final List<Integer> applicationId
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
            "select DISTINCT app.idApplication AS idApplication, " +
                    " userAuthorized.idUser AS idUser, " +
                    " userAttribute.attribute.idAttribute AS idAttribute, " +
                    " CASE when attributeLanguage.name IS NULL THEN userAttribute.attribute.name ELSE attributeLanguage.name END AS name, " +
                    " CASE when attributeLanguage.description IS NULL THEN userAttribute.attribute.description ELSE attributeLanguage.description END AS description, " +
                    " userAttribute.attribute_value AS value " +
                    " FROM ApplicationEntity app " +
                    " INNER JOIN app.roles roleAuthorized ON roleAuthorized.name = com.onsystem.wscapp.pantheon.commons.Constants.AUTORIZED_ROLE_NAME " +
                    " INNER JOIN roleAuthorized.user userAuthorized ON userAuthorized.deleteDate IS NULL " +
                    " INNER JOIN userAuthorized.userAttribute userAttribute ON userAttribute.attribute.application.idApplication = app.idApplication " +
                    " LEFT JOIN userAttribute.attribute.attributeLanguages attributeLanguage ON attributeLanguage.language.idLanguage = :languageId " +
                    " WHERE app.idApplication IN (:applicationIds) AND app.deleteDate IS NULL "
    )
    List<AttributeUserDataProjection> findAttributeDataUsersByIdApplication(
            final List<Integer> applicationIds,
            final int languageId
    );

    @Query(
            " SELECT " +
                    " app.idApplication AS idApplication, " +
                    " userAuthorized.idUser AS idUser, " +
                    " CASE WHEN roleUserAuthorizedLanguage.name IS NULL THEN roleUserAuthorized.name ELSE roleUserAuthorizedLanguage.name END AS name, " +
                    " CASE WHEN roleUserAuthorizedLanguage.description IS NULL THEN roleUserAuthorized.description ELSE roleUserAuthorizedLanguage.description END AS description, " +
                    " roleUserAuthorized.idRole AS idRole " +
                    " FROM ApplicationEntity  app " +
                    " INNER JOIN app.roles roleAuthorized ON roleAuthorized.name = com.onsystem.wscapp.pantheon.commons.Constants.AUTORIZED_ROLE_NAME " +
                    " INNER JOIN roleAuthorized.user userAuthorized ON userAuthorized.deleteDate IS NULL " +
                    " INNER JOIN userAuthorized.role roleUserAuthorized " +
                    " LEFT JOIN roleUserAuthorized.roleLanguages roleUserAuthorizedLanguage ON roleUserAuthorizedLanguage.language.idLanguage = :languageId " +
                    " WHERE app.idApplication IN (:applicationIds) "
    )
    List<UserRoleInfoProjection> findUserRoleByApplicationIdIn(
            final List<Integer> applicationIds,
            int languageId
    );

    @Query(
            " SELECT " +
                    " app.idApplication AS idApplication, " +
                    " userAuthorized.idUser AS idUser, " +
                    " CASE WHEN permissionUserAuthorizedLanguage.name IS NULL THEN permissionUserAuthorized.name ELSE permissionUserAuthorizedLanguage.name END AS name, " +
                    " CASE WHEN permissionUserAuthorizedLanguage.description IS NULL THEN permissionUserAuthorized.description ELSE permissionUserAuthorizedLanguage.description END AS description, " +
                    " permissionUserAuthorized.idPermission AS idPermission " +
                    " FROM ApplicationEntity  app " +
                    " INNER JOIN app.roles roleAuthorized ON roleAuthorized.name = com.onsystem.wscapp.pantheon.commons.Constants.AUTORIZED_ROLE_NAME " +
                    " INNER JOIN roleAuthorized.user userAuthorized ON userAuthorized.deleteDate IS NULL " +
                    " INNER JOIN userAuthorized.permission permissionUserAuthorized " +
                    " LEFT JOIN permissionUserAuthorized.permissionLanguages permissionUserAuthorizedLanguage ON permissionUserAuthorizedLanguage.language.idLanguage = :languageId " +
                    " WHERE app.idApplication IN (:applicationIds) "
    )
    List<UserPermissionInfoProjection> findUserPermissionByApplicationIdIn(
            final List<Integer> applicationIds,
            int languageId
    );


}
