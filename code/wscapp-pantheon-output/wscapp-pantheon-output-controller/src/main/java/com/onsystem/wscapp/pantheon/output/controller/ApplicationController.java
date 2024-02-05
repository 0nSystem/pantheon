package com.onsystem.wscapp.pantheon.output.controller;

import com.onsystem.wscapp.pantheon.output.api.dto.applications.AllInfoApplicationDTO;
import com.onsystem.wscapp.pantheon.output.api.dto.applications.ApplicationInfoDTO;
import com.onsystem.wscapp.pantheon.output.api.interfaces.services.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    private IApplicationService iApplicationService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public AllInfoApplicationDTO findApplicationById(
            final @RequestParam Integer applicationId,
            final @RequestParam Integer languageId
    ) {

        return iApplicationService.findByIdApplication(applicationId, languageId);
    }




}
