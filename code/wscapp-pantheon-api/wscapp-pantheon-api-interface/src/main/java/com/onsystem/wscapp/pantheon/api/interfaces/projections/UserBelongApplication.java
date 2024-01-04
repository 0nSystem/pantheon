package com.onsystem.wscapp.pantheon.api.interfaces.projections;

import jakarta.validation.constraints.NotNull;

public interface UserBelongApplication {


    @NotNull
    Integer getIdUser();

    @NotNull
    Integer getIdApplication();
}
