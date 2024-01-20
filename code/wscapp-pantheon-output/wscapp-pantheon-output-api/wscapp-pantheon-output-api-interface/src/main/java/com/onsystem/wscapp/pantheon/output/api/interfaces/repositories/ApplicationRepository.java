package com.onsystem.wscapp.pantheon.output.api.interfaces.repositories;

import com.onsystem.wscapp.pantheon.commons.entity.applications.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Integer> {
}
