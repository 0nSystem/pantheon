package com.onsystem.wscapp.pantheon.api.interfaces.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.onsystem.wscapp.pantheon.api.interfaces.entity.Constants.SCHEME_PUBLIC;

@Entity
@Table(schema = SCHEME_PUBLIC, name = "user_application")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(UserApplicationId.class)
public class UserApplicationEntity {

    @Id
    private Integer idUser;
    @Id
    private Integer idApplication;
}
