package com.onsystem.wscapp.pantheon.input.api.interfaces;

import com.onsystem.wscapp.pantheon.commons.entity.applications.ApplicationEntity;
import com.onsystem.wscapp.pantheon.commons.entity.applications.AttributeEntity;
import com.onsystem.wscapp.pantheon.commons.entity.applications.PermissionEntity;
import com.onsystem.wscapp.pantheon.commons.entity.applications.RoleEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.ApplicationRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.AttributeRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.spublic.LanguageRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.PermissionRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.RoleRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static com.onsystem.wscapp.pantheon.input.api.interfaces.MockData.DataEntityMockSchemeUserDTO.USER_ENTITY_CREATE_MOCK;

@TestConfiguration
public class DataInsertedBeforeTest {

    @Bean
    public Integer idLanguage(@Autowired LanguageRepository languageRepository) {
        return languageRepository.save(MockData.DataMockSchemeApplicationEntities.LANGUAGE_MOCK).getIdLanguage();
    }

    @Bean
    public Integer idUser(@Autowired UserRepository userRepository) {
        return userRepository.save(USER_ENTITY_CREATE_MOCK).getIdUser();
    }

    @Bean
    public Integer idApplication(@Autowired ApplicationRepository applicationRepository) {
        return applicationRepository.save(MockData.DataMockSchemeApplicationEntities.APPLICATION_MOCK).getIdApplication();
    }

    @Bean
    public Integer idPermission(@Autowired PermissionRepository permissionRepository, @Autowired Integer idApplication) {
        final PermissionEntity entity = MockData.DataMockSchemeApplicationEntities.PERMISSION_MOCK_BUILDER
                .application(ApplicationEntity.builder().idApplication(idApplication).build())
                .build();
        return permissionRepository.save(entity).getIdPermission();
    }

    @Bean
    public Integer idRole(@Autowired RoleRepository roleRepository, @Autowired Integer idApplication) {
        final RoleEntity entity = MockData.DataMockSchemeApplicationEntities.ROLE_MOCK_BUILDER
                .application(ApplicationEntity.builder().idApplication(idApplication).build())
                .build();
        return roleRepository.save(entity).getIdRole();
    }

    @Bean
    public Integer idAttribute(@Autowired AttributeRepository attributeRepository, @Autowired Integer idApplication) {
        final AttributeEntity attributeEntity = MockData.DataMockSchemeApplicationEntities.ATTRIBUTE_MOCK_BUILDER
                .application(ApplicationEntity.builder().idApplication(idApplication).build())
                .build();

        return attributeRepository.save(attributeEntity).getIdAttribute();
    }

}
