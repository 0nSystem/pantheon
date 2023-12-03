package com.onsystem.wscapp.pantheon.api.interfaces.repositories.applications;

import com.onsystem.wscapp.pantheon.api.dto.applications.attribute.UpdateAttributeDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.AttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepository extends JpaRepository<AttributeEntity, Integer> {


    @Modifying
    @Query("UPDATE AttributeEntity SET name = :#{#dto.name}, description = :#{#dto.description} " +
            "WHERE idAttribute = :#{#dto.idAttribute}")
    void update(final @Param("dto") UpdateAttributeDTO updateAttribute);


}
