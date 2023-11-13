package com.onsystem.wscapp.pantheon.api.interfaces.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

import static com.onsystem.wscapp.pantheon.api.interfaces.Constants.SCHEME_APPLICATION;
import static com.onsystem.wscapp.pantheon.api.interfaces.Constants.TABLE_ROLE;

@Entity
@Table(schema = SCHEME_APPLICATION, name = TABLE_ROLE)
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRole;

    @Positive
    private Integer idApplication;

    @NotEmpty
    @Size(max = 100)
    private String name;

    @NotEmpty
    @Size(max = 255)
    private String description;


    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idApplication", columnDefinition = "idApplication", insertable = false, updatable = false)
    private ApplicationEntity application;


}
