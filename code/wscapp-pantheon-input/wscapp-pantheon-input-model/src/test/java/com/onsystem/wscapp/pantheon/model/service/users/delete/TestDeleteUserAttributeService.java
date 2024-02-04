package com.onsystem.wscapp.pantheon.model.service.users.delete;

import com.onsystem.wscapp.pantheon.input.api.dto.users.DeleteUserAttributeDTO;
import com.onsystem.wscapp.pantheon.commons.entity.applications.AttributeEntity;
import com.onsystem.wscapp.pantheon.commons.entity.users.UserAttributeEntity;
import com.onsystem.wscapp.pantheon.commons.entity.users.UserEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.users.UserAttributeRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.users.delete.IDeleteUserAttributeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
class TestDeleteUserAttributeService {
    @Autowired
    private IDeleteUserAttributeService deleteUserAttributeService;
    @Autowired
    private UserAttributeRepository userAttributeRepository;

    @Autowired
    private Integer idUser;
    @Autowired
    private Integer idAttribute;

    @Test
    @Rollback
    void testCaseCorrectDelete() {
        //TODO
        final UserAttributeEntity userAttributeEntity = UserAttributeEntity.builder()
                .user(UserEntity.builder().idUser(idUser).build())
                .attribute(AttributeEntity.builder().idAttribute(idAttribute).build())
                .attribute_value("value1")
                .build();
        userAttributeRepository.save(userAttributeEntity);

        final long countBeforeDelete = userAttributeRepository.count();

        final DeleteUserAttributeDTO deleteUserAttributeDTO = DeleteUserAttributeDTO.builder()
                .idAttribute(Set.of(idAttribute))
                .idUser(idUser)
                .build();
        deleteUserAttributeService.removeAttributesAssigned(Set.of(deleteUserAttributeDTO));

        final long countAfterDelete = userAttributeRepository.count();

        Assertions.assertEquals(countBeforeDelete - 1, countAfterDelete);

    }
}
