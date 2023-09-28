package com.onsystem.wscapp.pantheon.api.interfaces.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPermissionId {
    private Integer idUser;
    private Integer idPermission;
}
