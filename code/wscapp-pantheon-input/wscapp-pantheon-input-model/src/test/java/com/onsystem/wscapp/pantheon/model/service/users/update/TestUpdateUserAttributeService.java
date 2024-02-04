package com.onsystem.wscapp.pantheon.model.service.users.update;

import com.onsystem.wscapp.pantheon.input.api.dto.users.UpdateUserAttributeDTO;
import com.onsystem.wscapp.pantheon.commons.entity.applications.AttributeEntity;
import com.onsystem.wscapp.pantheon.commons.entity.users.UserAttributeEntity;
import com.onsystem.wscapp.pantheon.commons.entity.users.UserEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.users.UserAttributeRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.users.update.IUpdateUserAttributeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.ThrowingConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
class TestUpdateUserAttributeService {

    @Autowired
    private IUpdateUserAttributeService iUpdateUserAttributeService;
    @Autowired
    private UserAttributeRepository userAttributeRepository;
    @Autowired
    private Integer idUser;
    @Autowired
    private Integer idAttribute;
    @Autowired
    private Integer idApplication;


    @Test
    void testCorrectCaseUpdateUserAttribute() throws Throwable {
        final UserAttributeEntity userAttributeEntity = UserAttributeEntity.builder()
                .user(UserEntity.builder().idUser(idUser).build())
                .attribute(AttributeEntity.builder().idAttribute(idAttribute).build())
                .attribute_value("value1")
                .build();

        final UserAttributeEntity userAttributeSaved = userAttributeRepository.save(userAttributeEntity);

        final UpdateUserAttributeDTO updateUserAttributeDTO = UpdateUserAttributeDTO.builder()
                .idUserAttribute(userAttributeSaved.getIdUserAttribute())
                .attribute_value("asdd")
                .build();

        iUpdateUserAttributeService.updateUserAttribute(Set.of(updateUserAttributeDTO));

        final UserAttributeEntity getterToCompareWithUpdate = userAttributeRepository.findById(userAttributeSaved.getIdUserAttribute())
                .orElseThrow();

        consumerComparatorCorrectCaseUpdateUserAttributeEntity(updateUserAttributeDTO)
                .accept(getterToCompareWithUpdate);


    }

    final ThrowingConsumer<UserAttributeEntity> consumerComparatorCorrectCaseUpdateUserAttributeEntity(final UpdateUserAttributeDTO updateUserAttribute) {
        return userAttributeEntity -> {
            Assertions.assertEquals(updateUserAttribute.getIdUserAttribute(), userAttributeEntity.getIdUserAttribute());
            Assertions.assertEquals(updateUserAttribute.getAttribute_value(), userAttributeEntity.getAttribute_value());
        };
    }

}
