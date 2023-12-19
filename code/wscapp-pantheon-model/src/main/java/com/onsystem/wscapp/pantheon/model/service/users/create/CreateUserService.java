package com.onsystem.wscapp.pantheon.model.service.users.create;

import com.onsystem.wscapp.pantheon.api.dto.users.CreateAfterUserDTO;
import com.onsystem.wscapp.pantheon.api.dto.users.CreateUserDTO;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.users.UserEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.exceptions.InfoException;
import com.onsystem.wscapp.pantheon.api.interfaces.mapper.users.MapperUserEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.users.UserRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.users.create.ICreateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateUserService implements ICreateUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MapperUserEntity mapperUserEntity;


    @Override
    public CreateAfterUserDTO createUser(CreateUserDTO createUser) {
        validationUserNotExistEmailAndLogin(List.of(createUser));

        //FIXME remove createUserDTO
        //FIXME LOWER CASE?
        final UserEntity userEntity = mapperUserEntity.createToEntity(createUser);
        final UserEntity userSaved = userRepository.save(userEntity);
        return mapperUserEntity.entityToCreateAfter(userSaved);
    }

    @Override
    public List<CreateAfterUserDTO> createUsers(List<CreateUserDTO> createUser) {
        validationUserNotExistEmailAndLogin(createUser);

        //FIXME remove createUserDTO
        //FIXME LOWER CASE?
        final List<UserEntity> userEntities = createUser.stream()
                .map(mapperUserEntity::createToEntity)
                .toList();
        final List<UserEntity> userEntitiesSaved = userRepository.saveAll(userEntities);

        return userEntitiesSaved.stream()
                .map(mapperUserEntity::entityToCreateAfter)
                .toList();
    }

    /**
     * Validation username and logins and mail with database
     * and in list params
     *
     * @param createUser
     * @throws InfoException
     */
    private void validationUserNotExistEmailAndLogin(List<CreateUserDTO> createUser) throws InfoException {
        final List<String> login = createUser.stream().map(CreateUserDTO::getLogin).toList();
        final List<String> email = createUser.stream().map(CreateUserDTO::getEmail).toList();

        int count = userRepository.countByEmailInOrLoginInAndDeleteIdUserIsNot(email, login);
        if (count > 0) {
            //FIXME
            throw new InfoException("Error");
        }
    }
}
