package com.onsystem.wscapp.pantheon.api.request;

import lombok.NoArgsConstructor;

public class Constants {

    @NoArgsConstructor
    public static class ApiUrl {
        public static final String START_URI = "/api/v2";
        public static final String LANGUAGE_CONTROLLER = START_URI + "/language";
        public static final String USER_CONTROLLER = START_URI + "/user";
        public static final String APPLICATION_CONTROLLER = START_URI + "/application";
        public static final String ROLE_CONTROLLER = START_URI + "/role";
        public static final String PERMISSION_CONTROLLER = START_URI + "/permission";
        public static final String ATTRIBUTE_CONTROLLER = START_URI + "/attribute";
        public static final String USER_ROLE_CONTROLLER = START_URI + "/userRole";
        public static final String USER_APPLICATION_CONTROLLER = START_URI + "/userApplication";
        public static final String USER_PERMISSION_CONTROLLER = START_URI + "/userPermission";
        public static final String ROLE_PERMISSION_CONTROLLER = START_URI + "/rolePermission";



    }

    //TODO move translation messages errors this package
    @NoArgsConstructor
    public static class ErrorValidationMessages {
        public static final String NOT_NULL = "NOT_NULL";
        public static final String NOT_EMPTY = "NOT_EMPTY";
        public static final String SIZE = "SIZE";
        public static final String MIN = "MIN";
        public static final String MAX = "MAX";

    }
}
