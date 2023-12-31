package com.onsystem.wscapp.pantheon.api.interfaces.repositories.applications;

import com.onsystem.wscapp.pantheon.api.dto.applications.permission.UpdatePermissionLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.PermissionLanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.PermissionLanguageKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PermissionLanguageRepository extends JpaRepository<PermissionLanguageEntity, PermissionLanguageKeyEntity> {

    @Modifying
    @Query("UPDATE PermissionLanguageEntity SET name = :#{#dto.name}, description = :#{#dto.description} " +
            "WHERE permission.idPermission = :#{#dto.idPermission} AND language.idLanguage = :#{#dto.idLanguage}")
    void update(final @Param("dto") UpdatePermissionLanguageDTO updatePermission);

    @Modifying
    @Query("DELETE FROM PermissionLanguageEntity WHERE permission.idPermission = :permissionId AND language.idLanguage IN (:languageIds) ")
    void deleteByIdPermissionAndIdLanguageIn(int permissionId, Collection<Integer> languageIds);

    @Modifying
    @Query("DELETE FROM PermissionLanguageEntity WHERE permission.idPermission IN (:permissionIds)")
    void deleteByIdPermissionIn(Collection<Integer> permissionIds);
}

