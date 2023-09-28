package com.onsystem.wscapp.pantheon.api.interfaces.repositories;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationLanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationLanguageKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationLanguageRepository extends JpaRepository<ApplicationLanguageEntity, ApplicationLanguageKeyEntity> {

    Optional<ApplicationLanguageEntity> findByName(String name);

}
