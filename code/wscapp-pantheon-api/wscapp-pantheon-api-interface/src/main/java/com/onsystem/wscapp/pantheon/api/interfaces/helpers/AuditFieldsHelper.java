package com.onsystem.wscapp.pantheon.api.interfaces.helpers;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.AuditFieldsEntity;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Service
public interface AuditFieldsHelper {

    default Consumer<AuditFieldsEntity> consumerSetHighFieldsAudit(final Timestamp ts, final int userId) {
        return auditFieldsEntity -> {
            auditFieldsEntity.setHighDate(ts);
            auditFieldsEntity.setHighIdUser(userId);
        };
    }

    default <T extends AuditFieldsEntity> List<T> setHighFieldsAudit(final Timestamp ts, final int userId, T... auditEntity) {
        final Consumer<AuditFieldsEntity> setHighFields = consumerSetHighFieldsAudit(ts, userId);

        return Arrays.stream(auditEntity)
                .peek(setHighFields)
                .toList();
    }
}
