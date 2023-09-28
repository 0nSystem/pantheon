package com.onsystem.wscapp.pantheon.api.response.language;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttributeResponse {


    private int idAttribute;

    private int idApplication;

    private String name;

    private String description;
}
