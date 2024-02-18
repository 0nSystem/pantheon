package com.onsystem.wscapp.pantheon.output.api.interfaces.projections;


public interface PermissionInfoProjection {

    Integer getIdApplication();
    Integer getIdPermission();
    String getName();
    String getDescription();

}
