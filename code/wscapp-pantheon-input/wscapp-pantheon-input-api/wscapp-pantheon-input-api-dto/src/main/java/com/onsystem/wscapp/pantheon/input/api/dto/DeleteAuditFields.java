
package com.onsystem.wscapp.pantheon.input.api.dto;

import java.sql.Timestamp;

public interface DeleteAuditFields {

    Timestamp getDeleteDate();
    void setDeleteDate(Timestamp ts);
    Integer getDeleteIdUser();
    void setDeleteIdUser(Integer userId);
}
