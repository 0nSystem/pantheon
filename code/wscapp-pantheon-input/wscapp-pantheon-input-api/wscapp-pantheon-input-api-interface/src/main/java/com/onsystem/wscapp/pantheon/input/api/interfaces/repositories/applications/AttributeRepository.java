package com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications;

import com.onsystem.wscapp.pantheon.input.api.dto.applications.attribute.UpdateAttributeDTO;
import com.onsystem.wscapp.pantheon.input.api.interfaces.entity.applications.AttributeEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.projections.AttributeBelongApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AttributeRepository extends JpaRepository<AttributeEntity, Integer> {


    @Modifying
    @Query("UPDATE AttributeEntity SET name = :#{#dto.name}, description = :#{#dto.description} " +
            "WHERE idAttribute = :#{#dto.idAttribute}")
    void update(final @Param("dto") UpdateAttributeDTO updateAttribute);

    @Query("SELECT idAttribute AS idAttribute, application.idApplication AS idApplication FROM AttributeEntity WHERE idAttribute IN (:attributeIds)")
    Set<AttributeBelongApplication> findAttributesBelongApplicationByIdAttributeIn(final Set<Integer> attributeIds);


}
