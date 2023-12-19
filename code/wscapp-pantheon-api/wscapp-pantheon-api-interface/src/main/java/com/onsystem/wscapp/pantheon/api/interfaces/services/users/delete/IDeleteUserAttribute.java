package com.onsystem.wscapp.pantheon.api.interfaces.services.users.delete;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.List;

public interface IDeleteUserAttribute {


    void removeAttributesAssigned(final @NotEmpty List<Integer> attributesIds,
                                  final @Positive int userId);
}
