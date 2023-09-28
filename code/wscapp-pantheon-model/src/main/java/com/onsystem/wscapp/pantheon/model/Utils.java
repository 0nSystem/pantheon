package com.onsystem.wscapp.pantheon.model;

import com.onsystem.wscapp.pantheon.api.interfaces.AuditFieldsEntity;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Utils {

    static Consumer<AuditFieldsEntity> consumerSetHighFieldsAudit(final Timestamp ts, final int userId) {
        return auditFieldsEntity -> {
            auditFieldsEntity.setHighDate(ts);
            auditFieldsEntity.setHighIdUser(userId);
        };
    }

    public static <T extends AuditFieldsEntity> List<T> setHighFieldsAudit(final Timestamp ts, final int userId, T... auditEntity) {
        final Consumer<AuditFieldsEntity> setHighFields = consumerSetHighFieldsAudit(ts,userId);

        return Arrays.stream(auditEntity)
                .peek(setHighFields)
                .toList();
    }

}
