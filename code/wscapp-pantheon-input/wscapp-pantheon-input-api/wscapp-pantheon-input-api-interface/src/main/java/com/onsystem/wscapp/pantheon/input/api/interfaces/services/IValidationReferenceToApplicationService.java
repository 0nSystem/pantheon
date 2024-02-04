package com.onsystem.wscapp.pantheon.input.api.interfaces.services;

import com.onsystem.wscapp.pantheon.input.api.interfaces.exceptions.InfoException;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.Set;

public interface IValidationReferenceToApplicationService {


    void validationReferenceRolePermissionBelongApplication(final @Positive int idRole,
                                                            final @NotEmpty Set<Integer> idsPermissions)
            throws InfoException;

}
