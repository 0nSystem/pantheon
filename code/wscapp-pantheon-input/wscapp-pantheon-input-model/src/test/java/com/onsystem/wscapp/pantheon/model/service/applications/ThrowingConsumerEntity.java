package com.onsystem.wscapp.pantheon.model.service.applications;

import com.onsystem.wscapp.pantheon.input.api.interfaces.entity.applications.RolePermissionEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.ThrowingConsumer;

public class ThrowingConsumerEntity {


    public static ThrowingConsumer<RolePermissionEntity> caseDefaultCorrectPermissionAddingRole(
            final int roleId,
            final int permissionId
    ) {
        return rolePermissionEntity -> {
            Assertions.assertEquals(permissionId, rolePermissionEntity.getPermission().getIdPermission());
            Assertions.assertEquals(roleId, rolePermissionEntity.getRole().getIdRole());
        };
    }
}
