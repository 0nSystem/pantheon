package com.onsystem.wscapp.pantheon.input.api.interfaces.mapper.users;


import com.onsystem.wscapp.pantheon.input.api.dto.users.CreateUserAttributeDTO;
import com.onsystem.wscapp.pantheon.commons.entity.users.UserAttributeEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.ThrowingConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Stream;

import static com.onsystem.wscapp.pantheon.input.api.interfaces.MockData.DataCreateMockSchemeUserDTO.CREATE_USER_ATTRIBUTE_MOCK;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MapperUserAttributeTestEntity {

    @Autowired
    private MapperUserAttributeEntity mapperUserAttributeEntity;


    private static ThrowingConsumer<UserAttributeEntity> caseDefaultCorrectCreateUserAttributeToEntity(final CreateUserAttributeDTO createUserAttribute) {
        return userAttributeEntity -> {
            Assertions.assertNotNull(userAttributeEntity.getAttribute().getIdAttribute());
            Assertions.assertEquals(createUserAttribute.getAttributeId(), userAttributeEntity.getAttribute().getIdAttribute());
            Assertions.assertNotNull(userAttributeEntity.getUser());
            Assertions.assertEquals(createUserAttribute.getUserId(), userAttributeEntity.getUser().getIdUser());
            Assertions.assertTrue(createUserAttribute.getValue().contains(userAttributeEntity.getAttribute_value()));

        };
    }


    @TestFactory
    Stream<DynamicTest> testCaseCorrectCase() {
        final CreateUserAttributeDTO createUserAttribute = CREATE_USER_ATTRIBUTE_MOCK;
        createUserAttribute.setUserId(1);

        final List<UserAttributeEntity> userAttributeEntity = mapperUserAttributeEntity.createToEntity(createUserAttribute);

        return DynamicTest.stream(
                userAttributeEntity.stream(),
                uae -> String.format(
                        "UserAttribute assign user: %s ,attribute: %s, value: %s ",
                        uae.getUser().getIdUser(),
                        uae.getAttribute().getIdAttribute(),
                        uae.getAttribute_value()
                ),
                caseDefaultCorrectCreateUserAttributeToEntity(createUserAttribute)
        );

    }

}
