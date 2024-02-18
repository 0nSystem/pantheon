package com.onsystem.wscapp.pantheon.output.api.interfaces.projections;

import java.sql.Timestamp;
import java.util.List;

public interface ApplicationInfoProjection {

    Integer getIdApplication();

    String getName();

    String getDescription();

    Timestamp getHighDate();

    Integer getHighIdUser();

}
