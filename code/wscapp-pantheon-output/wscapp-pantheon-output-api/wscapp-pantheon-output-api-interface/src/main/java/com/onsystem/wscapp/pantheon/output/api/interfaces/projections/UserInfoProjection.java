package com.onsystem.wscapp.pantheon.output.api.interfaces.projections;

import java.sql.Timestamp;


public interface UserInfoProjection {

    Integer getIdApplication();
    Integer getIdUser();
    String getName();
    String getSurname();
    String getLogin();
    String getEmail();
    Timestamp getHighDate();
    Integer getHighIdUser();


}
