package com.onsystem.wscapp.pantheon.model.helpers;

import com.onsystem.wscapp.pantheon.api.interfaces.projections.AttributeBelongApplication;
import com.onsystem.wscapp.pantheon.api.interfaces.projections.UserBelongApplication;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.applications.AttributeRepository;
import com.onsystem.wscapp.pantheon.api.interfaces.repositories.users.UserRoleRepository;
import com.onsystem.wscapp.pantheon.model.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Attribute;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BelongApplicationsHelper {

    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private AttributeRepository attributeRepository;


    /**
     * @param userId
     * @return map (key = idUser, value = IdsApplications)
     */
    public Map<Integer, List<Integer>> getUserBelongApplication(final Set<Integer> userId) {
        final Set<UserBelongApplication> userBelongApplications = userRoleRepository.findIdsApplicationByUser(userId, Constants.AUTORIZED_ROLE_NAME);
        return userBelongApplications.stream()
                .collect(Collectors.groupingBy(UserBelongApplication::getIdUser,
                        Collectors.mapping(UserBelongApplication::getIdApplication, Collectors.toList())));
    }

    /**
     * @param attributesIds
     * @return map (key = IdApplication, value = IdsAttributes)
     */
    public Map<Integer, List<Integer>> getAttributeBelongApplication(final Set<Integer> attributesIds) {
        final Set<AttributeBelongApplication> attributeBelongApplications = attributeRepository.findAttributesBelongApplicationByIdAttributeIn(attributesIds);

        attributeBelongApplications.stream().findFirst().get().getIdApplication();

        return attributeBelongApplications.stream()
                .collect(Collectors.groupingBy(AttributeBelongApplication::getIdApplication,
                        Collectors.mapping(AttributeBelongApplication::getIdAttribute, Collectors.toList())));

    }


}
