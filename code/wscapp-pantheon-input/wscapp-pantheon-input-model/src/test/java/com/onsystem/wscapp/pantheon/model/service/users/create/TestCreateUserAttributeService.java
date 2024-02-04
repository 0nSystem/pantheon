package com.onsystem.wscapp.pantheon.model.service.users.create;

import com.onsystem.wscapp.pantheon.input.api.dto.users.CreateUserAttributeDTO;
import com.onsystem.wscapp.pantheon.commons.entity.applications.ApplicationEntity;
import com.onsystem.wscapp.pantheon.commons.entity.applications.RoleEntity;
import com.onsystem.wscapp.pantheon.commons.entity.users.UserAttributeEntity;
import com.onsystem.wscapp.pantheon.commons.entity.users.UserEntity;
import com.onsystem.wscapp.pantheon.commons.entity.users.UserRoleEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.applications.RoleRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.users.UserAttributeRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.users.UserRoleRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.users.create.ICreateUserAttributeService;
import com.onsystem.wscapp.pantheon.input.model.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static com.onsystem.wscapp.pantheon.commons.Constants.AUTORIZED_ROLE_DESCRIPTION;
import static com.onsystem.wscapp.pantheon.commons.Constants.AUTORIZED_ROLE_NAME;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class TestCreateUserAttributeService {

    @Autowired
    private ICreateUserAttributeService iCreateUserAttributeService;
    @Autowired
    private UserAttributeRepository userAttributeRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private Integer idUser;
    @Autowired
    private Integer idAttribute;
    @Autowired
    private Integer idApplication;


    @Test
    void testCorrectCreateUserAttributeValue() {
        final RoleEntity roleAuthorization = roleRepository.save(
                RoleEntity.builder()
                        .application(ApplicationEntity.builder().idApplication(idApplication).build())
                        .name(AUTORIZED_ROLE_NAME)
                        .description(AUTORIZED_ROLE_DESCRIPTION)
                        .build());
        userRoleRepository.save(
                UserRoleEntity.builder()
                        .role(roleAuthorization)
                        .user(UserEntity.builder().idUser(idUser).build())
                        .build());

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
