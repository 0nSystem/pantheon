package com.onsystem.wscapp.pantheon.api.interfaces.repositories;

import com.onsystem.wscapp.pantheon.api.dto.role.UpdateRoleLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.RoleLanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.RoleLanguageKeyEntity;
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
    void deleteByRoleIdRoleAndLanguageIdLanguageIn(int roleId, Collection<Integer> languageIds);

    @Modifying
    void deleteByRoleIdRoleIn(Collection<Integer> roleIds);
}
