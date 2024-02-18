package com.onsystem.wscapp.pantheon.output.api.dto.users;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AttributeUserDataDTO {

    private Integer attributeId;
    private Integer userId;
    private String name;
    private String description;
    private List<String> values;
}
