package com.onsystem.wscapp.pantheon.output.controller;

import com.onsystem.wscapp.pantheon.output.api.dto.users.UserInfoDTO;
import com.onsystem.wscapp.pantheon.output.api.interfaces.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @GetMapping("findUsersByApplicationId")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Set<UserInfoDTO> findUsersByApplicationId(final @RequestParam Integer applicationId){
        return iUserService.findUsersByIdApplicationWithValidationIfCanShowThisInfo(applicationId);
    }

    @GetMapping("findUsersByPermissionsAndApplication")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Set<UserInfoDTO> findUsersByPermissionAndApplication(final @RequestParam Integer applicationId,
                                                                final @RequestParam List<Integer> permissionIds) {
        return iUserService
                .findUsersByIdApplicationAndPermissionsWithValidationIfCanShowThisInfo(applicationId,permissionIds);
    }

    @GetMapping("findUsersByRolesAndApplication")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Set<UserInfoDTO> findUsersByRolesAndApplication(final @RequestParam Integer applicationId,
                                                                final @RequestParam List<Integer> roleIds) {
        return iUserService
                .findUsersByIdApplicationAndRoleWithValidationIfCanShowThisInfo(applicationId,roleIds);
    }

    @GetMapping("findUsersByIdApplicationAndIdAttribute")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Set<UserInfoDTO> findUsersByIdApplicationAndIdAttribute(final @RequestParam Integer applicationId,
                                                           final @RequestParam Integer attributeId) {
        return iUserService
                .findUsersByIdApplicationAndIdAttributeWithValidationIfCanShowThisInfo(applicationId,attributeId);
    }



}
