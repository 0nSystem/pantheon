package com.onsystem.wscapp.pantheon.api.interfaces.entity;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApplicationKeyEntity {

    private Integer idUser;
    private Integer idApplication;
}
