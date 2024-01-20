package com.onsystem.wscapp.pantheon.input.model.service.users.create;

import com.onsystem.wscapp.pantheon.input.api.dto.users.CreateUserAttributeDTO;
import com.onsystem.wscapp.pantheon.commons.entity.users.UserAttributeEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.exceptions.InfoException;
import com.onsystem.wscapp.pantheon.input.api.interfaces.mapper.users.MapperUserAttributeEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.AttributeRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.RoleRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.users.UserAttributeRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.users.UserRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.users.create.ICreateUserAttributeService;
import com.onsystem.wscapp.pantheon.input.model.helpers.BelongApplicationsHelper;
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

        final List<UserAttributeEntity> attributeEntities = createUserAttributes.stream()
                .flatMap(createAttributeEntity -> mapperUserAttributeEntity.createToEntity(createAttributeEntity).stream())
                .toList();

        userAttributeRepository.saveAll(attributeEntities);

        return createUserAttributes;
    }

    private void validationUserInApplicationThisIdsAttributes(List<CreateUserAttributeDTO> createUserAttribute) {
        final Map<Integer, List<Integer>> mapIdUserIdsAttributesToCreate = createUserAttribute.stream()
                .collect(Collectors.groupingBy(CreateUserAttributeDTO::getUserId,
                        Collectors.mapping(CreateUserAttributeDTO::getAttributeId, Collectors.toList())));

        final Map<Integer, List<Integer>> mapIdUserBelongIdsApplications = belongApplicationsHelper.getUserBelongApplication(mapIdUserIdsAttributesToCreate.keySet());

        final Set<Integer> idsAllAttributes = mapIdUserIdsAttributesToCreate.values().stream()
                .flatMap(Collection::stream).collect(Collectors.toSet());
        final Map<Integer, Integer> mapIdAttributeBelongApplication = belongApplicationsHelper.getAttributeBelongApplication(idsAllAttributes);

        final List<String> errors = validateUsersAttributes(
                mapIdUserIdsAttributesToCreate, mapIdUserBelongIdsApplications, mapIdAttributeBelongApplication
        );
        if (CollectionUtils.isNotEmpty(errors)) {
            //TODO peding
            System.out.println(errors);
            throw new InfoException("");
        }

    }

    private List<String> validateUsersAttributes(
            final Map<Integer, List<Integer>> mapIdUserIdsAttributesToCreate,
            final Map<Integer, List<Integer>> mapIdUserBelongIdsApplications,
            final Map<Integer, Integer> mapIdAttributeBelongApplication
    ) {

        final List<String> errors = new ArrayList<>();

        if (MapUtils.isEmpty(mapIdUserIdsAttributesToCreate)) {
            //TODO
            errors.add("Empty mapIdUserIdsAttributesToCreate");
        }


        for (final Map.Entry<Integer, List<Integer>> entryIdUserIdsAttributesToCreate : mapIdUserIdsAttributesToCreate.entrySet()) {
            final Integer idUser = entryIdUserIdsAttributesToCreate.getKey();
            final List<Integer> idsAttributes = entryIdUserIdsAttributesToCreate.getValue();

            for (final Integer idAttributes : idsAttributes) {
                final Integer attributeBelongIdApplication = mapIdAttributeBelongApplication.get(idAttributes);
                if (attributeBelongIdApplication != null) {
                    final List<Integer> userBelongApplications = mapIdUserBelongIdsApplications.get(idUser);
                    if (CollectionUtils.isEmpty(userBelongApplications)
                            || !userBelongApplications.contains(attributeBelongIdApplication)) {
                        //TODO

                        errors.add(String.format("User: %s not belong applications", idUser));
                    }

                } else {
                    //TODO

                    errors.add(String.format("Error attribute id %s not belong applications", idAttributes));
                }

            }

        }


        return errors;
    }
}
