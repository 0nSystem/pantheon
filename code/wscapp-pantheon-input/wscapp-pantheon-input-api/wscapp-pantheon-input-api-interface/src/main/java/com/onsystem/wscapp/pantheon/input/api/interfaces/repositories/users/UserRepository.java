package com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.users;

import com.onsystem.wscapp.pantheon.commons.entity.users.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    //TODO update
    @Modifying
    @Query("UPDATE UserEntity SET deleteDate = :deleteDate, deleteIdUser = :deleteIdUser WHERE idUser IN (:userIds)")
    void delete(final @Param("userIds") Collection<Integer> userIds,
                final @Param("deleteDate") Timestamp deleteDate,
                final @Param("deleteIdUser") Integer deleteIdUser);


    @Query("SELECT COUNT(*) FROM UserEntity WHERE email IN (:emails) OR login IN (:logins) AND deleteDate IS NULL ")
    int countByEmailInOrLoginInAndDeleteIdUserIsNot(
            final List<String> emails,
            final List<String> logins
    );
}
