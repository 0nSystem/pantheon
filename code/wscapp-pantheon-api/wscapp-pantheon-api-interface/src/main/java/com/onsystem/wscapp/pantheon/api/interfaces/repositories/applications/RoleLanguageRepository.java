package com.onsystem.wscapp.pantheon.api.interfaces.repositories.applications;

import com.onsystem.wscapp.pantheon.api.dto.applications.role.UpdateRoleLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.RoleLanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.RoleLanguageKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RoleLanguageRepository extends JpaRepository<RoleLanguageEntity, RoleLanguageKeyEntity> {

    @Modifying
    @Query("UPDATE RoleLanguageEntity SET name = :#{#dto.name}, description = :#{#dto.description} " +
            "WHERE role.idRole = :#{#dto.idRole} AND language.idLanguage = :#{#dto.idLanguage}")
    void update(final @Param("dto") UpdateRoleLanguageDTO updateRoleLanguage);

    @Modifying
    @Query("DELETE FROM RoleLanguageEntity WHERE role.idRole = :roleId AND language.idLanguage IN (:languageIds) ")
    void deleteByRoleIdRoleAndLanguageIdLanguageIn(int roleId, Collection<Integer> languageIds);

    @Modifying
    @Query("DELETE FROM RoleLanguageEntity WHERE role.idRole IN (:roleIds) ")
    void deleteByIdRoleIn(Collection<Integer> roleIds);
}
