package com.onsystem.wscapp.pantheon.output.api.interfaces.repositories;

import com.onsystem.wscapp.pantheon.commons.entity.users.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {


}
