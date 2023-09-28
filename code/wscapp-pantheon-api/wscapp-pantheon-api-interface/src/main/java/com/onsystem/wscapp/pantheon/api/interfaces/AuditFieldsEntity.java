package com.onsystem.wscapp.pantheon.api.interfaces;

import java.sql.Timestamp;

public interface AuditFieldsEntity {

    Timestamp getHighDate();
    void setHighDate(Timestamp ts);
    Integer getHighIdUser();
    void setHighIdUser(Integer userId);

    Timestamp getDeleteDate();
    void setDeleteDate(Timestamp ts);
    Integer getDeleteIdUser();
    void setDeleteIdUser(Integer userId);

}
