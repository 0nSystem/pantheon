package com.onsystem.wscapp.pantheon.input.api.dto.users;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUserAttributeDTO {

    @Positive
    private int idUserAttribute;

    @NotEmpty
    private String attribute_value;

}
