package com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications;

import com.onsystem.wscapp.pantheon.commons.entity.applications.ApplicationLanguageEntity;
import com.onsystem.wscapp.pantheon.commons.entity.applications.ApplicationLanguageKeyEntity;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.application.UpdateApplicationLanguageDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    void update(final @NotNull @Param("dto") UpdateApplicationLanguageDTO updateApplicationLanguage);


    @Modifying
    @Query("DELETE FROM ApplicationLanguageEntity WHERE application.idApplication = :applicationId AND language.idLanguage IN (:languageIds) ")
    void deleteByIdApplicationAndIdLanguageIn(final @Positive int applicationId,
                                              final @NotEmpty Collection<Integer> languageIds);

}
