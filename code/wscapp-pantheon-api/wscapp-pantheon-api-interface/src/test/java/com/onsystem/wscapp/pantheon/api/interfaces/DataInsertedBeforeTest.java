package com.onsystem.wscapp.pantheon.api.interfaces;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.ApplicationEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.AttributeEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.PermissionEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.RoleEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.applications.ApplicationRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.applications.AttributeRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.spublic.LanguageRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.applications.PermissionRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.applications.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class DataInsertedBeforeTest {

    @Bean
    public Integer idLanguage(@Autowired LanguageRepository languageRepository) {
        return languageRepository.save(MockData.DataMockSchemeApplicationEntities.LANGUAGE_MOCK).getIdLanguage();
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
