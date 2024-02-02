package com.onsystem.wscapp.pantheon.output.api.interfaces.repositories;

import com.onsystem.wscapp.pantheon.commons.entity.applications.ApplicationEntity;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Integer> {


    @Query(" SELECT " +
            " app.idApplication AS idApplication, " +
            " CASE WHEN appl.name IS NOT NULL THEN appl.name ELSE app.name END AS name," +
            " CASE WHEN appl.description IS NOT NULL THEN  appl.description ELSE app.description END AS description, " +
            " app.highDate AS highDate, app.highIdUser AS highIdUser " +
            " FROM ApplicationEntity app " +
            " LEFT JOIN app.applicationLanguages appl ON appl.language.idLanguage = :languageId " +
            " WHERE app.idApplication IN (:applicationIds)")
        //TODO DEleteDate?
    List<ApplicationInfoProjection> findApplicationInfoById(
            final int languageId,
            final List<Integer> applicationIds
    );

    @Query(" SELECT" +
            " app.idApplication," +
            " perm.idPermission," +
            " CASE WHEN perml.name IS NOT NULL THEN perml.name ELSE perm.name END AS NAME," +
            " CASE WHEN perml.description IS NOT NULL THEN perml.description ELSE perm.description END AS DESCRIPTION" +
            " FROM ApplicationEntity app" + //Can from directly permission
            " INNER JOIN app.permissions perm" +
            " LEFT JOIN perm.permissionLanguages perml ON perml.language.idLanguage = :idIdioma" +
            " WHERE app.idApplication IN (:applicationsIds)"
    )
    List<PermissionInfoProjection> findPermissionInfoProjectionByIdApplicationIn(
            final int idIdioma,
            final Collection<Integer> applicationsIds);


    @Query(" SELECT" +
            " app.idApplication," +
            " roles.idRole," +
            " CASE WHEN rolesl.name IS NOT NULL THEN rolesl.name ELSE roles.name END AS NAME," +
            " CASE WHEN rolesl.description IS NOT NULL THEN rolesl.description ELSE roles.description END AS DESCRIPTION" +
            " FROM ApplicationEntity app" + //Can from directly roles
            " INNER JOIN app.roles roles" +
            " LEFT JOIN roles.roleLanguages rolesl ON rolesl.language.idLanguage = :idIdioma" +
            " WHERE app.idApplication IN (:applicationsIds)"
    )
    List<RoleInfoProjection> findRoleInfoProjectionByIdApplicationIn(
            final int idIdioma,
            final Collection<Integer> applicationsIds
    );


    @Query(" SELECT" +
            " app.idApplication," +
            " attr.idAttribute," +
            " CASE WHEN attrl.name IS NOT NULL THEN attrl.name ELSE attr.name END AS NAME," +
            " CASE WHEN attrl.description IS NOT NULL THEN attrl.description ELSE attr.description END AS DESCRIPTION" +
            " FROM ApplicationEntity app" + //Can from directly attributes
            " INNER JOIN app.attributes attr" +
            " LEFT JOIN attr.attributeLanguages attrl ON attrl.language.idLanguage = :idIdioma" +
            " WHERE app.idApplication IN (:applicationsIds)"
    )
    List<AttributeInfoProjection> findAttributeInfoProjectionByIdApplicationIn(
            final int idIdioma,
            final Collection<Integer> applicationsIds
    );


    @Query(
            "SELECT DISTINCT userWithRole " +
                    " FROM ApplicationEntity app " +
                    " INNER JOIN app.roles role ON role.idRole IN (:idRoles) " +
                    " INNER JOIN role.user userWithRole ON userWithRole.deleteDate IS NOT NULL" +
                    " WHERE app.idApplication = :applicationId " +
                    " AND userWithRole.deleteDate IS NOT NULL "
    )
    List<UserInfoProjection> findUserByIdApplicationAndIdRoleInAndDeleteDateIsNull(
            final int applicationId,
            final List<Integer> idRoles
    );



    @Query(
            "SELECT DISTINCT userWithPermission " +
                    " FROM ApplicationEntity app " +
                    " INNER JOIN app.permissions perm ON perm.idPermission IN (:idRoles) " +
                    " INNER JOIN perm.user userWithPermission ON userWithPermission.deleteDate IS NOT NULL" +
                    " WHERE app.idApplication = :applicationId " +
                    " AND userWithPermission.deleteDate IS NOT NULL "
    )
    List<UserInfoProjection> findUserByIdApplicationAndIdPermissionInAndDeleteDateIsNull(
            final int applicationId,
            final List<Integer> idRoles
    );

}
