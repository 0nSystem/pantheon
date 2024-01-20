package com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications;

import com.onsystem.wscapp.pantheon.input.api.dto.applications.attribute.UpdateAttributeLanguageDTO;
import com.onsystem.wscapp.pantheon.commons.entity.applications.AttributeLanguageEntity;
import com.onsystem.wscapp.pantheon.commons.entity.applications.AttributeLanguageKeyEntity;
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

    @Modifying
    @Query("DELETE FROM AttributeLanguageEntity WHERE attribute.idAttribute = :attributeId AND language.idLanguage IN (:languageIds)")
    void deleteByIdAttributeAndIdLanguageIn(final int attributeId,
                                            final Collection<Integer> languageIds);

    @Modifying
    @Query("DELETE FROM AttributeLanguageEntity WHERE attribute.idAttribute IN (:attributeIds)")
    void deleteByAttributeIdAttributeIn(Collection<Integer> attributeIds);
}
