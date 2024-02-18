package com.onsystem.wscapp.pantheon.input.model.service.users.update;

import com.onsystem.wscapp.pantheon.input.api.dto.users.UpdateUserAttributeDTO;
import com.onsystem.wscapp.pantheon.commons.entity.users.UserAttributeEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.users.UserAttributeRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.users.update.IUpdateUserAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UpdateUserAttributeService implements IUpdateUserAttributeService {

    @Autowired
    private UserAttributeRepository userAttributeRepository;


    @Override
    public void updateUserAttribute(Set<UpdateUserAttributeDTO> updateUserAttribute) {
        //TODO applicationId
        final Map<Integer, String> mapIdUserAttributeValueAttribute = updateUserAttribute.stream()
                .collect(Collectors.toMap(
                        UpdateUserAttributeDTO::getIdUserAttribute,
                        UpdateUserAttributeDTO::getAttribute_value,
                        (s, s2) -> s2)
                );

        final List<UserAttributeEntity> userAttribute = userAttributeRepository.findAllById(mapIdUserAttributeValueAttribute.keySet())
                .stream()
                .peek(ua -> ua.setAttribute_value(mapIdUserAttributeValueAttribute.get(ua.getIdUserAttribute())))
                .toList();

        userAttributeRepository.saveAll(userAttribute);
    }
}
