package com.onsystem.wscapp.pantheon.api.interfaces.entity.users;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.*;

import static com.onsystem.wscapp.pantheon.api.interfaces.Constants.SCHEME_USERS;

@Entity
@Table(schema = SCHEME_USERS, name = "user_application")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(UserApplicationKeyEntity.class)
public class UserApplicationEntity {

    @Id
    private Integer idUser;
    @Id
    private Integer idApplication;
}
