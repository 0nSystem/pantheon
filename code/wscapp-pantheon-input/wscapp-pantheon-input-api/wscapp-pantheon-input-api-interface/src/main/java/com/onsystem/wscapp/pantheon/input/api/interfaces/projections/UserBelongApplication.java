package com.onsystem.wscapp.pantheon.input.api.interfaces.projections;

import jakarta.validation.constraints.NotNull;

public interface UserBelongApplication {


    @NotNull
    Integer getIdUser();

    @NotNull
    Integer getIdApplication();
}
