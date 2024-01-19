package com.onsystem.wscapp.pantheon.input.api.dto.users;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserAttributeDTO {

    @Positive
    private int attributeId;
    @NotNull
    private int userId;

    @NotNull
    @NotEmpty
    private List<String> value;

}
