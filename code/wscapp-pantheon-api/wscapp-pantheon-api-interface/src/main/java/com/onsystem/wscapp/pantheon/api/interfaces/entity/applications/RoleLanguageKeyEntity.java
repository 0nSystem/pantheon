package com.onsystem.wscapp.pantheon.api.interfaces.entity.applications;


import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RoleLanguageKeyEntity {

    @NotNull
    private Integer role;
    @NotNull
    private Integer language;
}
