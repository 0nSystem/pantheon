package com.onsystem.wscapp.pantheon.api.dto.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteUserAttributeDTO {

    private Integer idUser;
    private Set<Integer> idAttribute;

}
