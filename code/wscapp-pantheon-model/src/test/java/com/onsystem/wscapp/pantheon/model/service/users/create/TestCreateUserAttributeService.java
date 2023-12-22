package com.onsystem.wscapp.pantheon.model.service.users.create;

import com.onsystem.wscapp.pantheon.api.dto.users.CreateUserAttributeDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserAttributeEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.users.UserAttributeRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.users.create.ICreateUserAttributeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class TestCreateUserAttributeService {

    @Autowired
    private ICreateUserAttributeService iCreateUserAttributeService;
    @Autowired
    private UserAttributeRepository userAttributeRepository;
    @Autowired
    private Integer idUser;
    @Autowired
    private Integer idAttribute;


    @Test
    void testCorrectCreateUserAttributeValue() {

        final CreateUserAttributeDTO createUserAttribute = CreateUserAttributeDTO.builder()
                .attributeId(idAttribute)
                .value(List.of("value1", "value2"))
                .userId(idUser)
                .build();

        iCreateUserAttributeService.assignAttribute(List.of(createUserAttribute));

        final Set<UserAttributeEntity> userAttributeEntity = userAttributeRepository
                .findByUserIdUserAndAndAttributeIdAttribute(idUser, idAttribute);

        Assertions.assertEquals(2, userAttributeEntity.size());

    }

}
