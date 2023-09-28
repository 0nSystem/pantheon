package com.onsystem.wscapp.pantheon.model.service;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.UserEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.exceptions.InfoException;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.UserRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.ISessionService;
import com.onsystem.wscapp.pantheon.api.interfaces.services.IUserService;
import com.onsystem.wscapp.pantheon.model.Constants;
import com.onsystem.wscapp.pantheon.model.TranslatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ISessionService sessionService;
    @Autowired
    private TranslatorUtils translatorUtils;


    @Override
    public List<UserEntity> create(UserEntity... userEntities) {
        final Timestamp ts = Timestamp.from(Instant.now());
        final Integer idUserInSession = sessionService.getUserIdInSession();

        final List<UserEntity> usersToCreate = Arrays.stream(userEntities)
                .peek(user -> {
                    user.setHighDate(ts);
                    user.setHighIdUser(idUserInSession);
                }).toList();

        return userRepository.saveAll(usersToCreate);
    }

    @Override
    public List<UserEntity> update(UserEntity... userEntities) {


        final List<Integer> idsUserToUpdate = Arrays.stream(userEntities)
                .map(UserEntity::getIdUser).toList();

        final Map<Integer, UserEntity> usersDB = userRepository.findByDeleteDateNullAndIdUserIn(idsUserToUpdate).stream()
                .collect(Collectors.toMap(UserEntity::getIdUser, userEntity -> userEntity, (t, t2) -> t));

        final List<UserEntity> userEntitiesToSave = Arrays.stream(userEntities)
                .filter(u -> usersDB.containsKey(u.getIdUser()))
                .peek(u -> {
                    final UserEntity userEntity = usersDB.get(u.getIdUser());
                    u.setHighDate(userEntity.getHighDate());
                    u.setHighIdUser(userEntity.getHighIdUser());
                }).toList();

        return userRepository.saveAll(userEntitiesToSave);
    }

    @Override
    public void delete(Collection<Integer> userId) {
        userRepository.delete(
                userId,
                Timestamp.from(Instant.now()),
                sessionService.getUserIdInSession());
    }


    @Override
    public UserEntity findById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    final Locale locale = sessionService.getLocaleInUser();
                    final String messageEntity = translatorUtils.createMessage(Constants.InfoMessages.ENTITY_USER, locale, null);
                    return new InfoException(translatorUtils.createMessage(Constants.InfoMessages.NOT_FOUND_WITH_MESSAGE, locale, messageEntity));
                });
    }
}
