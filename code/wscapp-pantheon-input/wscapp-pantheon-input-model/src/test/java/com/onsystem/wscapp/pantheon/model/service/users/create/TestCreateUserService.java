package com.onsystem.wscapp.pantheon.model.service.users.create;

import com.onsystem.wscapp.pantheon.input.api.dto.users.CreateAfterUserDTO;
import com.onsystem.wscapp.pantheon.input.api.dto.users.CreateUserDTO;
import com.onsystem.wscapp.pantheon.input.api.interfaces.exceptions.InfoException;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.users.UserRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.ISessionManager;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.users.create.ICreateUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.ThrowingConsumer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

import static com.onsystem.wscapp.pantheon.input.api.interfaces.MockData.DataCreateMockSchemeUserDTO.CREATE_USER_MOCK;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class TestCreateUserService {

    @Autowired
    private ICreateUserService iCreateUserService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ISessionManager iSessionManager;

    private Stream<Arguments> argumentsCorrectCreateUser() {
        final var a = CREATE_USER_MOCK;
        a.setLogin("1");
        a.setEmail("2");
        return Stream.of(
                Arguments.of(a)
        );
    }


    private ThrowingConsumer<CreateAfterUserDTO> throwingCorrectCreateUser(CreateUserDTO createUser) {
        return createAfterUserDTO -> {
            Assertions.assertEquals(createUser.getName(), createAfterUserDTO.getName());
            Assertions.assertEquals(createUser.getSurname(), createAfterUserDTO.getSurname());
            Assertions.assertEquals(createUser.getEmail(), createAfterUserDTO.getEmail());
            Assertions.assertEquals(createUser.getLogin(), createAfterUserDTO.getLogin());
            createUser.getHighIdUser()
                    .ifPresentOrElse(integer -> Assertions.assertEquals(integer, createAfterUserDTO.getHighIdUser()),
                            () -> Assertions.assertEquals(iSessionManager.currentIdUser(), createAfterUserDTO.getHighIdUser()));

        };
    }

    @MethodSource({"argumentsCorrectCreateUser"})
    @ParameterizedTest
    void testCorrectCreateUser(CreateUserDTO createUser) throws Throwable {
        //TODO Resolve transational error
        final CreateAfterUserDTO createAfterUser = iCreateUserService.createUser(createUser);

        throwingCorrectCreateUser(createUser)
                .accept(createAfterUser);
    }


    @Test
    void testErrorCreateUserExistFieldsWithMailOrLogin() {
        final var a = CREATE_USER_MOCK;
        a.setLogin("1");
        a.setEmail("2");
        iCreateUserService.createUser(a);

        Assertions.assertThrowsExactly(
                InfoException.class,
                () -> iCreateUserService.createUser(a)
        );
    }


}
