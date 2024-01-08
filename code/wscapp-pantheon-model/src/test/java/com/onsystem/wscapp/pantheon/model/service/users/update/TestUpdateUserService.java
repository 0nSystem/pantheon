package com.onsystem.wscapp.pantheon.model.service.users.update;

import com.onsystem.wscapp.pantheon.api.dto.users.UpdateUserDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.users.UserRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.users.update.IUpdateUserService;
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
class TestUpdateUserService {

    @Autowired
    private IUpdateUserService iUpdateUserService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Integer idUser;

    @Test
    void testCorrectCaseUpdateUser() throws Throwable {

        final UpdateUserDTO updateUserDTO = UpdateUserDTO.builder()
                .idUser(idUser)
                .email("update email")
                .login("update login")
                .name("update name")
                .surname("update surname")
                .build();

        iUpdateUserService.updateUser(Set.of(updateUserDTO));


        final UserEntity userEntityUpdated = userRepository.findById(idUser)
                .orElseThrow();

        consumerCaseCorrectUpdateUserEntity(updateUserDTO)
                .accept(userEntityUpdated);

    }


    private ThrowingConsumer<UserEntity> consumerCaseCorrectUpdateUserEntity(UpdateUserDTO updateUserDTO) {
        return userEntity -> {
            Assertions.assertNotNull(userEntity.getPassword());
            Assertions.assertEquals(updateUserDTO.getIdUser(), userEntity.getIdUser());
            Assertions.assertEquals(updateUserDTO.getEmail(), userEntity.getEmail());
            Assertions.assertEquals(updateUserDTO.getLogin(), userEntity.getLogin());
            Assertions.assertEquals(updateUserDTO.getName(), userEntity.getName());
            Assertions.assertEquals(updateUserDTO.getSurname(), userEntity.getSurname());
        };
    }

}
