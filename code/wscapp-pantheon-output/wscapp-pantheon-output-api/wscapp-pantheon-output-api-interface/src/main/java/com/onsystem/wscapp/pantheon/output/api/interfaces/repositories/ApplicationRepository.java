package com.onsystem.wscapp.pantheon.output.api.interfaces.repositories;

import com.onsystem.wscapp.pantheon.commons.entity.applications.ApplicationEntity;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.AttributeInfoProjection;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.PermissionInfoProjection;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.RoleInfoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Integer> {


    @Query(" SELECT" +
            " app.idApplication," +
            " CASE WHEN perml.name IS NOT NULL THEN perml.name ELSE perm.name END AS NAME," +
            " CASE WHEN perml.description IS NOT NULL THEN perml.description ELSE perm.description END AS DESCRIPTION" +
            " FROM ApplicationEntity app" +
            " INNER JOIN app.permissions perm" +
            " LEFT JOIN perm.permissionLanguages perml ON perml.language.idLanguage = :idIdioma" +
            " WHERE app.idApplication IN (:applicationsIds)"
    )
    List<PermissionInfoProjection> findPermissionInfoProjectionByIdApplicationIn(
            final int idIdioma,
            final Collection<Integer> applicationsIds);


    List<RoleInfoProjection> findRoleInfoProjectionByIdApplicationIn(final Collection<Integer> applicationsIds);


    List<AttributeInfoProjection> findAttributeInfoProjectionByIdApplicationIn(final Collection<Integer> applicationsIds);


}
