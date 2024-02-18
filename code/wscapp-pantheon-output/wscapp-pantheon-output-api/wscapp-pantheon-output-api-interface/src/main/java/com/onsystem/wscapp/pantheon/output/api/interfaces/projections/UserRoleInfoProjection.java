package com.onsystem.wscapp.pantheon.output.api.interfaces.projections;

public interface UserRoleInfoProjection {

    Integer getIdUser();
    Integer getIdApplication();

    Integer getIdRole();

    String getName();

    String getDescription();
}
