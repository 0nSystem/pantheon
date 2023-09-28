package com.onsystem.wscapp.pantheon.model.service;

import com.onsystem.wscapp.pantheon.api.interfaces.entity.ApplicationEntity;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.ApplicationRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.services.IApplicationService;
import com.onsystem.wscapp.pantheon.api.interfaces.services.ISessionService;
import com.onsystem.wscapp.pantheon.model.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Service
public class ApplicationService implements IApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private ISessionService sessionService;

    @Override
    public List<ApplicationEntity> create(ApplicationEntity... applicationEntities) {
        final int userId = sessionService.getUserIdInSession();
        final Timestamp ts = Timestamp.from(Instant.now());

        final List<ApplicationEntity> applicationToSave = Utils.setHighFieldsAudit(ts, userId, applicationEntities);

        return applicationRepository.saveAll(applicationToSave);
    }

    @Override
    public void update(ApplicationEntity... applicationEntities) {

        //TODO ver operaciones batch
        Arrays.stream(applicationEntities)
                .forEach(app ->
                        applicationRepository.update(
                                app.getName(), app.getDescription(), app.getIdApplication())
                );
    }

    @Override
    public ApplicationEntity findById(int applicationId) {
        //TODO
        return applicationRepository.findById(applicationId).orElseThrow();
    }

    @Override
    public void delete(Integer... applicationId) {
        final int userId = sessionService.getUserIdInSession();
        final Timestamp ts = Timestamp.from(Instant.now());

        applicationRepository.delete(ts, userId, List.of(applicationId));
    }
}
