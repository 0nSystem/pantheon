package com.onsystem.wscapp.pantheon.api.interfaces;

public class Constants {

    public static final String DB_NAME_MANAGEMENT = "management";
    public static final String SCHEME_APPLICATION = "applications";
    public static final String SCHEME_PUBLIC = "public";
    public static final String SCHEME_USERS = "users";

    public static final String TABLE_LANGUAGE = "language";
    public static final String TABLE_APPLICATION = "application";
    public static final String TABLE_APPLICATION_LANGUAGE = "application_language";
    public static final String TABLE_ROLE = "role";
    public static final String TABLE_ROLE_LANGUAGE = "role_language";
    public static final String TABLE_PERMISSION = "permission";
    public static final String TABLE_PERMISSION_LANGUAGE = "permission_language";
    public static final String TABLE_ROLE_PERMISSION = "role_permission";

    public static final String TABLE_ATTRIBUTE = "attribute";
    public static final String TABLE_ATTRIBUTE_LANGUAGE = "attribute_language";
    public static final String TABLE_USER = "\"user\"";
    public static final String TABLE_USER_PERMISSION = "user_permission";
    public static final String TABLE_USER_ROLE = "user_role";
    public static final String TABLE_USER_ATTRIBUTE = "user_attribute";


    public static final String ENDPOINT_APPLICATION_ROOT_CONTROLLER = "/application";
    public static final String ENDPOINT_ROLE_ROOT_CONTROLLER = "/role";
    public static final String ENDPOINT_PERMISSION_ROOT_CONTROLLER = "/permission";
    public static final String ENDPOINT_ATTRIBUTE_ROOT_CONTROLLER = "/attribute";
    public static final String ENDPOINT_USER_ROOT_CONTROLLER = "/user";
    public static final String ENDPOINT_USER_PERMISSION_ROOT_CONTROLLER = "/userPermission";
    public static final String ENDPOINT_USER_ROLE_ROOT_CONTROLLER = "/userRole";
    public static final String ENDPOINT_USER_ATTRIBUTE_ROOT_CONTROLLER = "/userAttribute";
    public static final String ENDPOINT_TO_LANGUAGE = "/language";
}
