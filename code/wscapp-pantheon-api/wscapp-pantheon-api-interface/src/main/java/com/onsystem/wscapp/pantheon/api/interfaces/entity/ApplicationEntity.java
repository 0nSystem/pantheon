package com.onsystem.wscapp.pantheon.api.interfaces.entity;


import com.onsystem.wscapp.pantheon.api.dto.DeleteAuditFields;
import com.onsystem.wscapp.pantheon.api.dto.HightAuditFields;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.sql.Timestamp;
import java.util.Set;

import static com.onsystem.wscapp.pantheon.api.interfaces.Constants.SCHEME_APPLICATION;
import static com.onsystem.wscapp.pantheon.api.interfaces.Constants.TABLE_APPLICATION;

@Entity
@Table(schema = SCHEME_APPLICATION, name = TABLE_APPLICATION)
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationEntity implements HightAuditFields, DeleteAuditFields {
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

    @ToString.Exclude
    @OneToMany(mappedBy = "application", fetch = FetchType.LAZY)
    private Set<ApplicationLanguageEntity> applicationLanguages;

    @ToString.Exclude
    @OneToMany(mappedBy = "application", fetch = FetchType.LAZY)
    private Set<RoleEntity> roles;

    @ToString.Exclude
    @OneToMany(mappedBy = "application", fetch = FetchType.LAZY)
    private Set<PermissionEntity> permissions;

    @ToString.Exclude
    @OneToMany(mappedBy = "application", fetch = FetchType.LAZY)
    private Set<AttributeEntity> attributes;
}
