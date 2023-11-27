package com.onsystem.wscapp.pantheon.api.interfaces.repositories;

import com.onsystem.wscapp.pantheon.api.dto.attribute.UpdateAttributeLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.AttributeLanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.AttributeLanguageKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AttributeLanguageRepository extends JpaRepository<AttributeLanguageEntity, AttributeLanguageKeyEntity> {


    @Modifying
    @Query("UPDATE AttributeLanguageEntity SET name = :#{#dto.name}, description = :#{#dto.description} " +
            "WHERE attribute.idAttribute = :#{#dto.idAttribute} AND language.idLanguage =:#{#dto.idLanguage}")
    void update(final @Param("dto") UpdateAttributeLanguageDTO updateAttributeLanguage);
}
