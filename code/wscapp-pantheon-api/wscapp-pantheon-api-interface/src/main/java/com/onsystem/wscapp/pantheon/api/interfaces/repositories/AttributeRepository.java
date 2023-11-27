package com.onsystem.wscapp.pantheon.api.interfaces.repositories;

import com.onsystem.wscapp.pantheon.api.dto.attribute.UpdateAttributeDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.AttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AttributeRepository extends JpaRepository<AttributeEntity, Integer> {


    @Modifying
    @Query("UPDATE AttributeEntity SET name = :#{#dto.name}, description = :#{#dto.description} " +
            "WHERE idAttribute = :#{#dto.idAttribute}")
    void update(final @Param("dto") UpdateAttributeDTO updateAttribute);
}
