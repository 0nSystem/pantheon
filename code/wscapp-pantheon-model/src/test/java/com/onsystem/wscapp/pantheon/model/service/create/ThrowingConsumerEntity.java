package com.onsystem.wscapp.pantheon.model.service.create;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.RolePermissionEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.ThrowingConsumer;

public class ThrowingConsumerEntity {


    public static ThrowingConsumer<RolePermissionEntity> caseDefaultCorrectPermissionAddingRole(
            final int roleId,
            final int permissionId
    ) {
        return rolePermissionEntity -> {
            Assertions.assertEquals(permissionId, rolePermissionEntity.getIdPermission());
            Assertions.assertEquals(roleId, rolePermissionEntity.getIdRole());
        };
    }
}
