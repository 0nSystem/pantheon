package com.onsystem.wscapp.pantheon.api.interfaces.projections;

import jakarta.validation.constraints.NotNull;

public interface AttributeBelongApplication {

    @NotNull
    Integer getIdAttribute();

    @NotNull
    Integer getIdApplication();
}
