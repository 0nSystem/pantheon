package com.onsystem.wscapp.pantheon.api.interfaces.entity.users;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAttributeKeyEntity {
    private Integer user;

    private Integer attribute;
}
