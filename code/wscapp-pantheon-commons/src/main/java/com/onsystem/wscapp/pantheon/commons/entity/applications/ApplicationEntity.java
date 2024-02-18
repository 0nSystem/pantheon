package com.onsystem.wscapp.pantheon.commons.entity.applications;


import com.onsystem.wscapp.pantheon.commons.Constants;
import com.onsystem.wscapp.pantheon.commons.dto.DeleteAuditFields;

import com.onsystem.wscapp.pantheon.commons.dto.HightAuditFields;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.sql.Timestamp;
import java.util.Set;



@Entity
@Table(schema = Constants.SCHEME_APPLICATION, name = Constants.TABLE_APPLICATION)
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
    @OneToMany(mappedBy = "application")
    private Set<ApplicationLanguageEntity> applicationLanguages;

    @ToString.Exclude
    @OneToMany(mappedBy = "application")
    private Set<RoleEntity> roles;

    @ToString.Exclude
    @OneToMany(mappedBy = "application")
    private Set<PermissionEntity> permissions;

    @ToString.Exclude
    @OneToMany(mappedBy = "application")
    private Set<AttributeEntity> attributes;
}
