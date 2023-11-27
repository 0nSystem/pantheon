package com.onsystem.wscapp.pantheon.api.interfaces;

import lombok.Getter;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class ArgumentsParams {
    @Getter
    private static Integer ID_APPLICATION;
    @Getter
    private static Integer ID_LANGUAGE;
    @Getter
    private static Integer ID_PERMISSION;
    @Getter
    private static Integer ID_ROLE;


    @Autowired
    public ArgumentsParams(
            Integer idApplication, Integer idLanguage,
            Integer idPermission, Integer idRole
    ) {
        ID_APPLICATION = idApplication;
        ID_LANGUAGE = idLanguage;
        ID_PERMISSION = idPermission;
        ID_ROLE = idRole;
    }

    public static Stream<Arguments> argumentsCreatePermissionLanguage() {
        return Stream.of(
                Arguments.of(ID_PERMISSION, ID_LANGUAGE,
                        MockData.DataMockSchemeApplicationDTO.CREATE_PERMISSION_LANGUAGE_MOCK_BUILDER.idLanguage(ID_LANGUAGE).build())
        );
    }
}
