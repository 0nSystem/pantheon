package com.onsystem.wscapp.pantheon.output.api.dto.applications;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ApplicationInfoDTO {

    private Integer idApplication;

    @NotEmpty
    private String name;
    private String description;

    @NotNull
    private Timestamp highDate;
    @NotNull
    @Positive
    private Integer highIdUser;

}
