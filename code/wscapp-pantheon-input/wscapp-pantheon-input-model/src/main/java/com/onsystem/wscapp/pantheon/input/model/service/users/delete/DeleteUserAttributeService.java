package com.onsystem.wscapp.pantheon.input.model.service.users.delete;

import com.onsystem.wscapp.pantheon.input.api.dto.users.DeleteUserAttributeDTO;
import com.onsystem.wscapp.pantheon.commons.entity.users.UserAttributeEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.users.UserAttributeRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.users.delete.IDeleteUserAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DeleteUserAttributeService implements IDeleteUserAttributeService {

    @Autowired
    private UserAttributeRepository userAttributeRepository;

    @Override
    public void removeAttributesAssigned(Set<DeleteUserAttributeDTO> deleteUserAttributes) {
        //TODO applicationId?
        final Set<UserAttributeEntity> userAttributeEntities = resolvedDeleteAttributes(deleteUserAttributes);

        userAttributeRepository.deleteAll(userAttributeEntities);

    }

    private Set<UserAttributeEntity> resolvedDeleteAttributes(Set<DeleteUserAttributeDTO> deleteUserAttributes) {

        final Map<Integer, Set<Integer>> mapIdUserIdsAttributesToDelete = deleteUserAttributes.stream()
                .collect(Collectors.toMap(
                        DeleteUserAttributeDTO::getIdUser,
                        DeleteUserAttributeDTO::getIdAttribute,
                        (o, o2) -> o2
                ));

        final Set<Integer> idsAllAttributes = mapIdUserIdsAttributesToDelete.values().stream()
                .flatMap(Collection::stream).collect(Collectors.toSet());
        final Set<UserAttributeEntity> userAttributeEntities = userAttributeRepository.findByUserIdUserInAndAndAttributeIdAttributeIn(
                mapIdUserIdsAttributesToDelete.keySet(), idsAllAttributes
        );
        final Map<Integer, Set<UserAttributeEntity>> mapIdUserAttributesEntitiesInDatabase = userAttributeEntities.stream()
                .collect(Collectors.groupingBy(a->a.getUser().getIdUser(), Collectors.toSet()));

        return discardAttributesNotRequireDeleteAndGetUserAttributeId(mapIdUserIdsAttributesToDelete, mapIdUserAttributesEntitiesInDatabase);
    }


    private Set<UserAttributeEntity> discardAttributesNotRequireDeleteAndGetUserAttributeId(
            final Map<Integer, Set<Integer>> mapIdUserIdsAttributesToDelete,
            final Map<Integer, Set<UserAttributeEntity>> mapIdUserAttributesEntities
    ) {

        final Set<UserAttributeEntity> userAttributeToRemove = new HashSet<>();

        for (final Map.Entry<Integer, Set<Integer>> entryIdUserIdsAttributes : mapIdUserIdsAttributesToDelete.entrySet()) {

            final Integer idUser = entryIdUserIdsAttributes.getKey();
            final Set<Integer> idsAttributes = entryIdUserIdsAttributes.getValue();

            final Set<UserAttributeEntity> userAttributeEntities = mapIdUserAttributesEntities.get(idUser);
            if (userAttributeEntities != null) {
                final Set<UserAttributeEntity> filteredContainsInUser = userAttributeEntities.stream()
                        .filter(uae -> idsAttributes.contains(uae.getAttribute().getIdAttribute()))
                        .collect(Collectors.toSet());
                userAttributeToRemove.addAll(filteredContainsInUser);
            }
        }


        return userAttributeToRemove;
    }


}
