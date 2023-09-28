package com.onsystem.wscapp.pantheon.api.interfaces.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApplicationId {

    private Integer idUser;
    private Integer idApplication;
}
