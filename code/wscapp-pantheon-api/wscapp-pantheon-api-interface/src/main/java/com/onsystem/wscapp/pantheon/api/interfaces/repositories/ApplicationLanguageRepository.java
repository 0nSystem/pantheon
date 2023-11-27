package com.onsystem.wscapp.pantheon.api.interfaces.repositories;

import com.onsystem.wscapp.pantheon.api.dto.application.UpdateApplicationLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationLanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationLanguageKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ApplicationLanguageRepository extends JpaRepository<ApplicationLanguageEntity, ApplicationLanguageKeyEntity> {

    Optional<ApplicationLanguageEntity> findByName(String name);


    @Modifying
    @Query("UPDATE ApplicationLanguageEntity SET name = :#{#dto.name}, description = :#{#dto.description} " +
            "WHERE application.idApplication = :#{#dto.idApplication} AND language.idLanguage = :#{#dto.idLanguage}")
    void update(final @Param("dto") UpdateApplicationLanguageDTO updateApplicationLanguage);

}
