package com.onsystem.wscapp.pantheon.output.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizationController {


    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;


    @GetMapping("/login/oauth2/code/oidc-client")
    public ResponseEntity<?> index(@RegisteredOAuth2AuthorizedClient("pantheon") OAuth2AuthorizedClient authorizedClient) {

        return ResponseEntity.ok()
                .build();
    }
}
