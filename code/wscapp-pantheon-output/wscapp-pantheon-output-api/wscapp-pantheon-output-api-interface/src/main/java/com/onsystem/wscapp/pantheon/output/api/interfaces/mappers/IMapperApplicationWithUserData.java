package com.onsystem.wscapp.pantheon.output.api.interfaces.mappers;

import com.onsystem.wscapp.pantheon.output.api.dto.applications.ApplicationDataDTO;
import com.onsystem.wscapp.pantheon.output.api.dto.users.ApplicationWithUsersData;
import com.onsystem.wscapp.pantheon.output.api.dto.users.UserDataInApplicationDTO;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.AttributeUserDataProjection;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.UserInfoProjection;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.UserPermissionInfoProjection;
import com.onsystem.wscapp.pantheon.output.api.interfaces.projections.UserRoleInfoProjection;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public abstract class IMapperApplicationWithUserData {

    @Autowired
    private IMapperApplication iMapperApplication;
    @Autowired
    private IMapperUser iMapperUser;


    public Set<ApplicationWithUsersData> toApplicationWithUserData(
            final Collection<ApplicationDataDTO> applicationData,
            final Collection<UserInfoProjection> userInApplications,
            final Collection<UserRoleInfoProjection> userRoleInApplications,
            final Collection<UserPermissionInfoProjection> userPermissionInApplication,
            final Collection<AttributeUserDataProjection> attributesUserData
    ) {
        final Set<ApplicationWithUsersData> applicationWithUsersData = new HashSet<>();

        final Map<Integer, ApplicationDataDTO> mapIdApplicationApplicationData = applicationData.stream()
                .collect(Collectors.toMap(
                        applicationDataDTO -> applicationDataDTO.getApplicationInfo().getIdApplication(),
                        o -> o,
                        (o, o2) -> o
                ));

        final Map<Integer, List<UserInfoProjection>> userInApplication = userInApplications
                .stream()
                .collect(Collectors.groupingBy(UserInfoProjection::getIdApplication));

        final Map<Pair<Integer, Integer>, List<UserRoleInfoProjection>> roleInApplicationAndUser = userRoleInApplications.stream()
                .collect(Collectors.groupingBy(role -> Pair.of(
                        role.getIdApplication(),
                        role.getIdUser())));

        final Map<Pair<Integer, Integer>, List<UserPermissionInfoProjection>> permissionUserInApplicationAndUser = userPermissionInApplication
                .stream()
                .collect(Collectors.groupingBy(userPermissionInfoProjection -> Pair.of(
                        userPermissionInfoProjection.getIdApplication(),
                        userPermissionInfoProjection.getIdUser())));
        final Map<Pair<Integer, Integer>, List<AttributeUserDataProjection>> userAttributeDataInApplicationAndUser = attributesUserData
                .stream()
                .collect(Collectors.groupingBy(attributeUserDataProjection -> Pair.of(
                        attributeUserDataProjection.getIdApplication(),
                        attributeUserDataProjection.getIdUser())));


        for (Map.Entry<Integer, ApplicationDataDTO> entryIdApplicationApplicationData : mapIdApplicationApplicationData.entrySet()) {
            final Integer applicationId = entryIdApplicationApplicationData.getKey();
            final ApplicationDataDTO appData = entryIdApplicationApplicationData.getValue();


            final List<UserInfoProjection> userDataInApplication = userInApplication.get(applicationId);

            if (userDataInApplication == null || userDataInApplication.isEmpty()) {
                applicationWithUsersData.add(ApplicationWithUsersData.builder()
                        .applicationData(appData)
                        .build());
                continue;
            }

            final List<UserDataInApplicationDTO> userDataThisApplicationGenerated = orderAndMappingUserDataInApplicationDTO(
                    userInApplications,
                    roleInApplicationAndUser,
                    permissionUserInApplicationAndUser,
                    userAttributeDataInApplicationAndUser
            );

            applicationWithUsersData.add(
                    ApplicationWithUsersData.builder()
                            .applicationData(appData)
                            .users(userDataThisApplicationGenerated)
                            .build()
            );


        }

        return applicationWithUsersData;
    }

    private List<UserDataInApplicationDTO> orderAndMappingUserDataInApplicationDTO(
            Collection<UserInfoProjection> userDataInApplication,
            Map<Pair<Integer, Integer>, List<UserRoleInfoProjection>> userRoleInApplicationAndUser,
            Map<Pair<Integer, Integer>, List<UserPermissionInfoProjection>> permissionUserInApplicationAndUser,
            Map<Pair<Integer, Integer>, List<AttributeUserDataProjection>> userAttributeDataInApplicationAndUser
    ) {
        final List<UserDataInApplicationDTO> users = new ArrayList<>();

        for (UserInfoProjection userDataProjection : userDataInApplication) {
            final Pair<Integer, Integer> applicationIdUserId = Pair.of(
                    userDataProjection.getIdApplication(), userDataProjection.getIdUser()
            );

            final UserDataInApplicationDTO userData = mappingUserDataInApplicationDTO(
                    userDataProjection,
                    userRoleInApplicationAndUser.get(applicationIdUserId),
                    permissionUserInApplicationAndUser.get(applicationIdUserId),
                    userAttributeDataInApplicationAndUser.get(applicationIdUserId)
            );
            users.add(userData);
        }

        return users;
    }

    private UserDataInApplicationDTO mappingUserDataInApplicationDTO(
            final UserInfoProjection userInfo,
            final Collection<UserRoleInfoProjection> userRoleData,
            final Collection<UserPermissionInfoProjection> userPermissionInfo,
            final Collection<AttributeUserDataProjection> userAttributeData
    ) {

        return UserDataInApplicationDTO.builder()
                .userInfo(iMapperUser.toDto(userInfo))
                .role(userRoleData.stream().map(iMapperUser::roleUserToDto).collect(Collectors.toSet()))
                .permissions(userPermissionInfo.stream().map(iMapperUser::permissionUserToDto).collect(Collectors.toSet()))
                .attribute(iMapperUser.attributeUserToDto(userAttributeData))
                .build();
    }
}
