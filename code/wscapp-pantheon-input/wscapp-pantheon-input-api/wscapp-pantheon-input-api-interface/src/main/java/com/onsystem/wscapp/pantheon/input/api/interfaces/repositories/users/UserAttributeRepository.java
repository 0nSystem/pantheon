package com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.users;

import com.onsystem.wscapp.pantheon.commons.entity.users.UserAttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserAttributeRepository extends JpaRepository<UserAttributeEntity, Integer> {

    Set<UserAttributeEntity> findByUserIdUserAndAndAttributeIdAttribute(final Integer userId, final Integer attributeId);

    Set<UserAttributeEntity> findByUserIdUserInAndAndAttributeIdAttributeIn(final Set<Integer> userId, final Set<Integer> attributeId);


}
