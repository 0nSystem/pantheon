package com.onsystem.wscapp.pantheon.api.interfaces.repositories;

import com.onsystem.wscapp.pantheon.api.dto.application.UpdateApplicationDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Optional;

@Repository
@Validated
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Integer> {

    Optional<ApplicationEntity> findByName(final @Valid @NotEmpty String name);


    @Modifying
    @Query("UPDATE ApplicationEntity SET deleteDate = :deleteDate, deleteIdUser = :deleteIdUser " +
            "WHERE idApplication IN (:applicationIds)")
    void updateDeleteDateAndDeleteIdUserByIdApplicationIn(
            final @Param("deleteDate") @Valid @NotNull Timestamp deleteDate,
            final @Param("deleteIdUser") @Valid @Positive int deleteIdUser,
            final @Param("applicationIds") @Valid @NotEmpty @Positive Collection<Integer> applicationIds
    );


    @Modifying
    @Query("UPDATE ApplicationEntity SET name = :#{#dto.name}, description = :#{#dto.description} " +
            "WHERE idApplication = :#{#dto.idApplication}")
    void update(final @Param("dto") UpdateApplicationDTO updateApplicationDTO);
}
