package com.onsystem.wscapp.pantheon.input.model.service.users.update;

import com.onsystem.wscapp.pantheon.input.api.dto.users.UpdateUserDTO;
import com.onsystem.wscapp.pantheon.input.api.interfaces.entity.users.UserEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.mapper.users.MapperUserEntity;
import com.onsystem.wscapp.pantheon.input.api.interfaces.repositories.users.UserRepository;
import com.onsystem.wscapp.pantheon.input.api.interfaces.services.users.update.IUpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UpdateUserService implements IUpdateUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MapperUserEntity mapperUserEntity;


    @Override
    public void updateUser(Set<UpdateUserDTO> updateUser) {
        //TODO applicationId
        final Map<Integer, UpdateUserDTO> mapIdUserUpdateUserDto = updateUser.stream()
                .collect(Collectors.toMap(
                        UpdateUserDTO::getIdUser,
                        updateUserDTO -> updateUserDTO,
                        (o, o2) -> o2
                ));

        final List<UserEntity> userEntities = userRepository.findAllById(mapIdUserUpdateUserDto.keySet());
        final List<UserEntity> userEntitiesMappedToUpdate = userEntities.stream()
                .map(userEntity -> mapperUserEntity.entityToUpdate(mapIdUserUpdateUserDto.get(userEntity.getIdUser()), userEntity))
                .toList();

        userRepository.saveAll(userEntitiesMappedToUpdate);

    }
}
