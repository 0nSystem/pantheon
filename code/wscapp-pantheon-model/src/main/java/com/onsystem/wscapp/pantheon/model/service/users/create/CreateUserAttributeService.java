package com.onsystem.wscapp.pantheon.model.service.users.create;

import com.onsystem.wscapp.pantheon.api.dto.users.CreateUserAttributeDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserAttributeEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.exceptions.InfoException;
import com.onsystem.wscapp.pantheon.api.interfaces.mapper.users.MapperUserAttributeEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.applications.AttributeRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.applications.RoleRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.users.UserAttributeRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.users.UserRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.users.create.ICreateUserAttributeService;
import com.onsystem.wscapp.pantheon.model.helpers.BelongApplicationsHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CreateUserAttributeService implements ICreateUserAttributeService {

    @Autowired
    private UserAttributeRepository userAttributeRepository;
    @Autowired
    private MapperUserAttributeEntity mapperUserAttributeEntity;
    @Autowired
    private AttributeRepository attributeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BelongApplicationsHelper belongApplicationsHelper;


    @Transactional
    @Override
    public List<CreateUserAttributeDTO> assignAttribute(List<CreateUserAttributeDTO> createUserAttributes) {
        validationUserInApplicationThisIdsAttributes(createUserAttributes);

        final List<UserAttributeEntity> attributeEntities = createUserAttributes.stream().map(mapperUserAttributeEntity::createToEntity)
                .flatMap(Collection::stream)
                .toList();

        userAttributeRepository.saveAll(attributeEntities);

        return createUserAttributes;
    }

    private void validationUserInApplicationThisIdsAttributes(List<CreateUserAttributeDTO> createUserAttribute) {
        final Map<Integer, List<Integer>> mapIdUserIdsAttributes = createUserAttribute.stream()
                .collect(Collectors.groupingBy(CreateUserAttributeDTO::getUserId,
                        Collectors.mapping(CreateUserAttributeDTO::getAttributeId, Collectors.toList())));

        final Map<Integer, List<Integer>> mapIdUserIdsApplications = belongApplicationsHelper.getUserBelongApplication(mapIdUserIdsAttributes.keySet());

        final Set<Integer> idsAllAttributes = mapIdUserIdsAttributes.values().stream()
                .flatMap(Collection::stream).collect(Collectors.toSet());
        final Map<Integer, List<Integer>> mapIdApplicationIdsAttributes = belongApplicationsHelper.getAttributeBelongApplication(idsAllAttributes);

        final List<String> errors = validateUsersAttributes(
                mapIdUserIdsApplications, mapIdApplicationIdsAttributes, mapIdUserIdsAttributes
        );
        if (CollectionUtils.isNotEmpty(errors)) {
            //TODO peding
            throw new InfoException("");
        }


    }

    //TODO
    private List<String> validateUsersAttributes(Map<Integer, List<Integer>> mapIdUserIdsApplications,
                                                 Map<Integer, List<Integer>> mapIdApplicationIdsAttributes,
                                                 Map<Integer, List<Integer>> mapIdUserIdsAttributes) {

        final List<String> errors = new ArrayList<>();

        if(MapUtils.isEmpty(mapIdUserIdsApplications)){
            errors.add("");//TODO
        }

        for (Map.Entry<Integer, List<Integer>> entryUserIdApplicationIds : mapIdUserIdsApplications.entrySet()) {
            int userId = entryUserIdApplicationIds.getKey();
            final List<Integer> applicationIds = entryUserIdApplicationIds.getValue();

            if (CollectionUtils.isEmpty(applicationIds)) {
                errors.add("User not permission to applications");
            }
            // Iterate over the applicationIds associated with the current userId
            for (int applicationId : applicationIds) {
                // Check if the applicationId exists in mapIdApplicationIdsAttributes
                final List<Integer> attributeIdsForApplication = mapIdApplicationIdsAttributes.get(applicationId);
                if (attributeIdsForApplication != null) {

                    // Check if the userId has attributes that belong to the current applicationId
                    final List<Integer> attributeIdsForUser = mapIdUserIdsAttributes.get(userId);
                    if (attributeIdsForUser != null) {

                        // Check if there are attributeIds for the user that do not belong to the application
                        List<Integer> invalidAttributeIds = attributeIdsForUser.stream()
                                .filter(attributeId -> !attributeIdsForApplication.contains(attributeId))
                                .collect(Collectors.toList());

                        if (!invalidAttributeIds.isEmpty()) {
                            //TODO
                            errors.add("User with ID " + userId + " has invalid attributes for Application ID " + applicationId
                                    + ": " + invalidAttributeIds);
                        }
                    }
                } else {
                    //TODO
                    errors.add("Invalid Application ID " + applicationId + " for User ID " + userId);
                }
            }
        }

        return errors;
    }
}
