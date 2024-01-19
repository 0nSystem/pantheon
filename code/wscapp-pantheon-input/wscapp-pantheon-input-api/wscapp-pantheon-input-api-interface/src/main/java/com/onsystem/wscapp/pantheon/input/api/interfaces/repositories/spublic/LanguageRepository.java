package com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.spublic;


import com.onsystem.wscapp.pantheon.input.api.interfaces.entity.spublic.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<LanguageEntity,Integer> {
}
