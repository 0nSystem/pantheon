package com.onsystem.wscapp.pantheon.input.api.dto;

import java.sql.Timestamp;

public interface HightAuditFields {

    Timestamp getHighDate();
    void setHighDate(Timestamp ts);
    Integer getHighIdUser();
    void setHighIdUser(Integer userId);


}
