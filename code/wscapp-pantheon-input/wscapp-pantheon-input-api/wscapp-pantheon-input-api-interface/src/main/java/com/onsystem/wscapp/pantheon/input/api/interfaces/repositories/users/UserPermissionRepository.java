package com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.users;

import com.onsystem.wscapp.pantheon.input.api.interfaces.entity.applications.PermissionEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.entity.users.UserPermissionEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.entity.users.UserPermissionKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserPermissionRepository extends JpaRepository<UserPermissionEntity, UserPermissionKeyEntity> {

    @Query(" SELECT permission FROM UserPermissionEntity WHERE permission.idPermission IN (:permissionId) AND user.idUser = :userId")
    Set<PermissionEntity> findPermissionByIdPermissionInAndIdUser(
            final Set<Integer> permissionId,
            final int userId);

    @Query("SELECT permission FROM UserPermissionEntity WHERE user.idUser = :userId AND permission.application.idApplication = :applicationId")
    Set<PermissionEntity> findPermissionEntitiesByIdUserAndIdApplication(final int userId,final int applicationId);
}
