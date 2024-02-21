package com.onsystem.wscapp.pantheon.input.client;


import com.onsystem.pantheon.input.client.doc.ApiClient;
import com.onsystem.pantheon.input.client.doc.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnBean(value = ApiClient.class)
public class ConfigurationClientPantheonInput {

    @Bean
    public ApplicationControllerApi applicationControllerApi(@Autowired ApiClient apiClient) {
        return new ApplicationControllerApi(apiClient);
    }

    @Bean
    public AttributeControllerApi attributeControllerApi(@Autowired ApiClient apiClient) {
        return new AttributeControllerApi(apiClient);
    }

    @Bean
    public PermissionControllerApi permissionControllerApi(@Autowired ApiClient apiClient) {
        return new PermissionControllerApi(apiClient);
    }

    @Bean
    public RoleControllerApi roleControllerApi(@Autowired ApiClient apiClient) {
        return new RoleControllerApi(apiClient);
    }

    @Bean
    public UserAttributeControllerApi userAttributeControllerApi(@Autowired ApiClient apiClient) {
        return new UserAttributeControllerApi(apiClient);
    }

    @Bean
    public UserControllerApi userControllerApi(@Autowired ApiClient apiClient) {
        return new UserControllerApi(apiClient);
    }

    @Bean
    public UserPermissionControllerApi userPermissionControllerApi(@Autowired ApiClient apiClient) {
        return new UserPermissionControllerApi(apiClient);
    }

    @Bean
    public UserRoleControllerApi userRoleControllerApi(@Autowired ApiClient apiClient) {
        return new UserRoleControllerApi(apiClient);
    }

}
