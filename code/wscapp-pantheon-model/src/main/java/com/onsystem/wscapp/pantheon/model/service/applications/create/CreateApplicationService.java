package com.onsystem.wscapp.pantheon.model.service.applications.create;

import com.onsystem.wscapp.pantheon.api.dto.applications.application.ApplicationDTO;
import com.onsystem.wscapp.pantheon.api.dto.applications.application.ApplicationLanguageDTO;
import com.onsystem.wscapp.pantheon.api.dto.applications.application.CreateApplicationDTO;
import com.onsystem.wscapp.pantheon.api.dto.applications.application.CreateApplicationLanguageDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.ApplicationEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.ApplicationLanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.PermissionEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.mapper.applications.MapperApplicationEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.mapper.applications.MapperApplicationLanguageEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.applications.ApplicationLanguageRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.applications.ApplicationRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.applications.PermissionRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.applications.create.ICreateApplicationService;
import com.onsystem.wscapp.pantheon.model.Constants;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CreateApplicationService implements ICreateApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private MapperApplicationEntity mapperApplicationEntity;

    @Autowired
    private ApplicationLanguageRepository applicationLanguageRepository;
    @Autowired
    private MapperApplicationLanguageEntity mapperApplicationLanguageEntity;

    @Autowired
    private PermissionRepository permissionRepository;

    @Transactional
    @Override
    public ApplicationDTO createApplication(final CreateApplicationDTO createApplication) {
        final ApplicationEntity applicationEntityMapped = mapperApplicationEntity.createToEntity(createApplication);
        final ApplicationEntity applicationInserted = applicationRepository.save(applicationEntityMapped);

        final PermissionEntity authorizedPermission = PermissionEntity.builder()
                .application(applicationInserted)
                .description(Constants.AUTORIZED_PERMISSION_NAME)
                .name(Constants.AUTORIZED_PERMISSION_DESCRIPTION)
                .build();

        permissionRepository.save(authorizedPermission);

        return mapperApplicationEntity.entityToDTO(applicationInserted);
    }

    @Transactional
    public Set<ApplicationLanguageDTO> createApplicationLanguages(final @Positive int applicationId,
                                                                  final Collection<CreateApplicationLanguageDTO> createApplicationLanguage) {

        final Collection<ApplicationLanguageEntity> applicationLanguageEntitiesMapped = createApplicationLanguage
                .stream()
                .map(modelApplicationLanguage -> mapperApplicationLanguageEntity.createToEntity(modelApplicationLanguage, applicationId))
                .collect(Collectors.toList());


        final List<ApplicationLanguageEntity> applicationLanguageEntitiesInserted = applicationLanguageRepository.saveAll(applicationLanguageEntitiesMapped);

        return applicationLanguageEntitiesInserted.stream()
                .map(applicationLanguagesInserted -> mapperApplicationLanguageEntity.toDto(applicationLanguagesInserted))
                .collect(Collectors.toSet());
    }
}
