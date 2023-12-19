package com.onsystem.wscapp.pantheon.api.interfaces.repositories.users;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserAttributeEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserAttributeKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAttributeRepository extends JpaRepository<UserAttributeEntity, UserAttributeKeyEntity> {


}
