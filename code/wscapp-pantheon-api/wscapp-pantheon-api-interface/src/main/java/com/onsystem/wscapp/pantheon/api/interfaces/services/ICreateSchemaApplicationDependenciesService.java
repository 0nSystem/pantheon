package com.onsystem.wscapp.pantheon.api.interfaces.services;

import com.onsystem.wscapp.pantheon.api.dto.application.*;
import com.onsystem.wscapp.pantheon.api.dto.attribute.AttributeLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.attribute.AttributeWithLanguagesDTO;
import com.onsystem.wscapp.pantheon.api.dto.attribute.CreateAttributeDTO;
import com.onsystem.wscapp.pantheon.api.dto.attribute.CreateAttributeLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.permission.*;
import com.onsystem.wscapp.pantheon.api.dto.role.CreateRoleLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.CreateRoleWithLanguagesAndPermissionWithLanguagesDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.RoleLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.role.RoleWithLanguagesAndPermissionWithLanguagesDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.Set;

/**
 * This service used to create entities reference by schema applications
 */
public interface ICreateSchemaApplicationDependenciesService {

    @NotNull ApplicationFullInfoWithLanguagesDTO createFullApplication(final @NotNull CreateFullApplicationDTO application);

}
