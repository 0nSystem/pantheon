package com.onsystem.wscapp.pantheon.api.interfaces.mapper.users;


import com.onsystem.wscapp.pantheon.api.dto.users.CreateUserAttributeDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserAttributeEntity;
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

import static com.onsystem.wscapp.pantheon.api.interfaces.MockData.DataCreateMockSchemeUserDTO.CREATE_USER_ATTRIBUTE_MOCK;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MapperUserAttributeTestEntity {

    @Autowired
    private MapperUserAttributeEntity mapperUserAttributeEntity;


    private static ThrowingConsumer<UserAttributeEntity> caseDefaultCorrectCreateUserAttributeToEntity(final CreateUserAttributeDTO createUserAttribute, int userId) {
        return userAttributeEntity -> {
            Assertions.assertNotNull(userAttributeEntity.getAttribute().getIdAttribute());
            Assertions.assertEquals(createUserAttribute.getAttributeId(), userAttributeEntity.getAttribute().getIdAttribute());
            Assertions.assertNotNull(userAttributeEntity.getUser());
            Assertions.assertEquals(userId, userAttributeEntity.getUser().getIdUser());
            Assertions.assertTrue(createUserAttribute.getValue().contains(userAttributeEntity.getValue()));

        };
    }


    @TestFactory
    Stream<DynamicTest> testCaseCorrectCase() {
        final CreateUserAttributeDTO createUserAttribute = CREATE_USER_ATTRIBUTE_MOCK;
        final int userId = 1;

        final List<UserAttributeEntity> userAttributeEntity = mapperUserAttributeEntity.createToEntity(createUserAttribute, userId);

        return DynamicTest.stream(
                userAttributeEntity.stream(),
                uae -> String.format(
                        "UserAttribute assign user: %s ,attribute: %s, value: %s ",
                        uae.getUser().getIdUser(),
                        uae.getAttribute().getIdAttribute(),
                        uae.getValue()
                ),
                caseDefaultCorrectCreateUserAttributeToEntity(createUserAttribute, userId)
        );

    }

}
