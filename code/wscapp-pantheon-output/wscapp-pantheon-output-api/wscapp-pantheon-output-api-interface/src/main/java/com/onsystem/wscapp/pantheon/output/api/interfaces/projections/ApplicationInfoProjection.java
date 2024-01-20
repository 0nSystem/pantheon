package com.onsystem.wscapp.pantheon.output.api.interfaces.projections;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.sql.Timestamp;

public interface ApplicationInfoProjection {

    Integer getIdApplication();

    String getName();

    String getDescription();

    Timestamp getHighDate();

    Integer getHighIdUser();


}
