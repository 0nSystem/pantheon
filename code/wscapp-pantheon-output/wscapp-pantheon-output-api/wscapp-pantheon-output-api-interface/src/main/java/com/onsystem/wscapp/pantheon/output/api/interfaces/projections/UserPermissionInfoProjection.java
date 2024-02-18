package com.onsystem.wscapp.pantheon.output.api.interfaces.projections;

public interface UserPermissionInfoProjection {

    Integer getIdUser();
    Integer getIdApplication();
    Integer getIdPermission();
    String getName();
    String getDescription();
}
