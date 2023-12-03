package com.onsystem.wscapp.pantheon.api.dto.applications.attribute;

import com.onsystem.wscapp.pantheon.api.dto.Constants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAttributeDTO {

    @NotEmpty(message = Constants.ErrorValidationMessages.NOT_EMPTY)
    @Size(max = 100, message = Constants.ErrorValidationMessages.MAX)
    private String name;
    @NotEmpty(message = Constants.ErrorValidationMessages.NOT_EMPTY)
    @Size(max = 255, message = Constants.ErrorValidationMessages.MAX)
    private String description;




}
