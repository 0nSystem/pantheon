package com.onsystem.wscapp.pantheon.controller;

import com.onsystem.wscapp.pantheon.GenericView;
import com.onsystem.wscapp.pantheon.api.interfaces.services.IUserApplicationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.onsystem.wscapp.pantheon.api.request.Constants.ApiUrl.USER_APPLICATION_CONTROLLER;

@Validated
@RestController
@RequestMapping(USER_APPLICATION_CONTROLLER)
public class UserApplicationController {

    @Autowired
    private IUserApplicationService iUserApplicationService;


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericView<?> create(final @RequestBody @Valid @NotEmpty Set<Integer> idsApplications,
                                 final @RequestBody @Valid @NotEmpty Set<Integer> idsUsers) {

        iUserApplicationService.insert(idsApplications, idsUsers);
        return GenericView.builder()
                .build();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericView<?> delete(final @RequestBody @Valid @NotEmpty Set<Integer> idsApplications,
                                 final @RequestBody @Valid @NotEmpty Set<Integer> idsUsers) {

        iUserApplicationService.delete(idsApplications, idsUsers);
        return GenericView.builder()
                .build();
    }

}
