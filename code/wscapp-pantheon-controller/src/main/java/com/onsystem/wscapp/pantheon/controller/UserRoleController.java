package com.onsystem.wscapp.pantheon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onsystem.wscapp.pantheon.GenericView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.onsystem.wscapp.pantheon.api.request.Constants.ApiUrl.USER_ROLE_CONTROLLER;

@RestController
@Validated
@RequestMapping(USER_ROLE_CONTROLLER)
public class UserRoleController {


    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private IUserRoleService iUserRoleService;


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericView<?> create(final @RequestParam @Valid Set<Integer> idsRoles,
                                 final @RequestParam @Valid Set<Integer> idsUser) {

        iUserRoleService.insert(idsRoles, idsUser);

        return GenericView.builder()
                .build();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericView<?> delete(final @RequestParam @Valid Set<Integer> idsRoles,
                                 final @RequestParam @Valid Set<Integer> idsUser) {

        iUserRoleService.delete(idsRoles, idsUser);

        return GenericView.builder()
                .build();
    }

}
