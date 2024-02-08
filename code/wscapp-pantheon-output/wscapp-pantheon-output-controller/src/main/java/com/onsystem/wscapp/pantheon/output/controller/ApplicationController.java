package com.onsystem.wscapp.pantheon.output.controller;

import com.onsystem.wscapp.pantheon.output.api.dto.applications.ApplicationDataDTO;
import com.onsystem.wscapp.pantheon.output.api.dto.users.ApplicationWithUsersData;
import com.onsystem.wscapp.pantheon.output.api.interfaces.services.IApplicationService;
import com.onsystem.wscapp.pantheon.output.api.interfaces.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    private IApplicationService iApplicationService;
    @Autowired
    private IUserService iUserService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApplicationDataDTO findApplicationById(
            final @RequestParam Integer applicationId,
            final @RequestParam Integer languageId
    ) {

        return iApplicationService.findByIdApplication(applicationId, languageId);
    }

    @GetMapping("/findApplicationWithUsersData")
    @ResponseStatus(HttpStatus.OK)
    public Set<ApplicationWithUsersData> findApplicationWithUserData(
            final @RequestParam List<Integer> applicationId,
            final @RequestParam Integer languageId
    ) {
        return iUserService.findApplicationWithUserData(applicationId,languageId);
    }


}
