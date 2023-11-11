package com.onsystem.wscapp.pantheon.api.interfaces.helpers;

import com.onsystem.wscapp.pantheon.api.dto.HightAuditFields;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.function.Consumer;

@Service
public interface AuditFieldsHelper {

    default Consumer<HightAuditFields> consumerSetHighFieldsAudit(final Timestamp ts, final int userId) {
        return auditFieldsEntity -> {
            auditFieldsEntity.setHighDate(ts);
            auditFieldsEntity.setHighIdUser(userId);
        };
    }



}
