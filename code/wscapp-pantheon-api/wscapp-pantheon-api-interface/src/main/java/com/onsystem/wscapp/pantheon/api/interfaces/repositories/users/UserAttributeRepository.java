package com.onsystem.wscapp.pantheon.api.interfaces.repositories.users;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserAttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserAttributeRepository extends JpaRepository<UserAttributeEntity, Integer> {

    Set<UserAttributeEntity> findByUserIdUserAndAndAttributeIdAttribute(final Integer userId, final Integer attributeId);

}
