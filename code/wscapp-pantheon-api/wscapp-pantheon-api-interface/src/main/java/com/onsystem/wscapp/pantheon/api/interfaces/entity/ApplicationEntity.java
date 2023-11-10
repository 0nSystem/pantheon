package com.onsystem.wscapp.pantheon.api.interfaces.entity;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.sql.Timestamp;

import static com.onsystem.wscapp.pantheon.api.interfaces.entity.Constants.SCHEME_APPLICATION;

@Entity
@Table(schema = SCHEME_APPLICATION, name = "application")
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationEntity implements AuditFieldsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idApplication;

    @NotEmpty
    private String name;
    private String description;

    @NotNull
    private Timestamp highDate;
    @NotNull
    @Positive
    private Integer highIdUser;

    @Nullable
    private Timestamp deleteDate;
    @Nullable
    @Positive
    private Integer deleteIdUser;
}
