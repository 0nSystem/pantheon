package com.onsystem.wscapp.pantheon.input.api.interfaces.mapper.users;


import com.onsystem.wscapp.pantheon.input.api.dto.users.CreateAfterUserDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.users.CreateUserDTO;
import com.onsystem.wscapp.pantheon.commons.entity.users.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.ThrowingConsumer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Stream;

import static com.onsystem.wscapp.pantheon.input.api.interfaces.MockData.DataCreateMockSchemeUserDTO.CREATE_USER_MOCK;
import static com.onsystem.wscapp.pantheon.input.api.interfaces.MockData.DataEntityMockSchemeUserDTO.USER_ENTITY_CREATE_MOCK;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MapperUserTestEntity {

    @Autowired
    private MapperUserEntity mapperUserEntity;


    private Stream<Arguments> testArgumentsCreateToEntity() {
        return Stream.of(
                Arguments.of(
                        CREATE_USER_MOCK
                )
        );
    }

    public static ThrowingConsumer<UserEntity> caseDefaultCorrectCreateUserToEntity(final CreateUserDTO createUser) {
        return user -> {
            Assertions.assertEquals(createUser.getName(), user.getName());
            Assertions.assertEquals(createUser.getSurname(), user.getSurname());
            Assertions.assertEquals(createUser.getEmail(), user.getEmail());
            Assertions.assertEquals(createUser.getLogin(), user.getLogin());
            Assertions.assertEquals(createUser.getPassword(), user.getPassword());
            Assertions.assertEquals(createUser.getHighIdUser(), user.getHighIdUser());
            Assertions.assertNotNull(user.getHighDate());
        };
    }

    @MethodSource({"testArgumentsCreateToEntity"})
    @ParameterizedTest
    void testCreateToEntity(CreateUserDTO createUser) throws Throwable {


        final UserEntity userEntity = mapperUserEntity.createToEntity(createUser);

        caseDefaultCorrectCreateUserToEntity(createUser)
                .accept(userEntity);

    }


    private Stream<Arguments> testArgumentEntityToCreateAfterEntity() {
        return Stream.of(
                Arguments.of(
                        USER_ENTITY_CREATE_MOCK
                )
        );
    }

    public static ThrowingConsumer<UserEntity> caseDefaultCorrectInsertedEntityDtoAfterInsert(final CreateAfterUserDTO createAfterUser) {
        return user -> {
            Assertions.assertEquals(createAfterUser.getName(), user.getName());
            Assertions.assertEquals(createAfterUser.getSurname(), user.getSurname());
            Assertions.assertEquals(createAfterUser.getEmail(), user.getEmail());
            Assertions.assertEquals(createAfterUser.getLogin(), user.getLogin());
            Assertions.assertEquals(createAfterUser.getHighIdUser(), user.getHighIdUser());
            Assertions.assertNotNull(user.getHighDate());
        };
    }

    @MethodSource({"testArgumentEntityToCreateAfterEntity"})
    @ParameterizedTest
    void testEntityToCreateAfter(final UserEntity userEntity) throws Throwable {

        final CreateAfterUserDTO createAfterUser = mapperUserEntity.entityToCreateAfter(userEntity);

        caseDefaultCorrectInsertedEntityDtoAfterInsert(createAfterUser)
                .accept(userEntity);
    }
}
