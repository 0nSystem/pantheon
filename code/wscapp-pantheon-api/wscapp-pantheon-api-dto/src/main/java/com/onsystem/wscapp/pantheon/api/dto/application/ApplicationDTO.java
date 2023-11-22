package com.onsystem.wscapp.pantheon.api.dto.application;

import com.onsystem.wscapp.pantheon.api.dto.Constants;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApplicationDTO {

    @Positive
    private int idApplication;

    @NotEmpty(message = Constants.ErrorValidationMessages.NOT_EMPTY)
    @Max(value = 100, message = Constants.ErrorValidationMessages.MAX)
    private String name;
    @NotEmpty(message = Constants.ErrorValidationMessages.NOT_EMPTY)
    @Max(value = 255, message = Constants.ErrorValidationMessages.MAX)
    private String description;

    @NotNull
    private Timestamp highDate;

    @NotNull
    @Positive
    private Integer highIdUser;

    private Timestamp deleteDate;
    @Positive
    private Integer deleteIdUser;
}
