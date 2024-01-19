package com.onsystem.wscapp.pantheon.input.model.service.applications.create;

import com.onsystem.wscapp.pantheon.input.api.dto.applications.application.ApplicationDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.application.ApplicationLanguageDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.application.CreateApplicationDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.applications.application.CreateApplicationLanguageDTO;
import com.onsystem.wscapp.pantheon.input.api.interfaces.entity.applications.ApplicationEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.entity.applications.ApplicationLanguageEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.entity.applications.RoleEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.mapper.applications.MapperApplicationEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.mapper.applications.MapperApplicationLanguageEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.ApplicationLanguageRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.ApplicationRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.RoleRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.applications.create.ICreateApplicationService;
import com.onsystem.wscapp.pantheon.input.model.Constants;
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
    private RoleRepository roleRepository;

    @Transactional
    @Override
    public ApplicationDTO createApplication(final CreateApplicationDTO createApplication) {
        final ApplicationEntity applicationEntityMapped = mapperApplicationEntity.createToEntity(createApplication);

        final ApplicationEntity applicationInserted = applicationRepository.save(applicationEntityMapped);

        roleRepository.save(RoleEntity.builder()
                .name(Constants.AUTORIZED_ROLE_NAME)
                .application(applicationInserted)
                .description(Constants.AUTORIZED_ROLE_DESCRIPTION)
                .build());


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
