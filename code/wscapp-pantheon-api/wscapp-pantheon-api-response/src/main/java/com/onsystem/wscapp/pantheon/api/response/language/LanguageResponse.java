package com.onsystem.wscapp.pantheon.api.response.language;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LanguageResponse {

    private Integer idLanguage;
    private String name;
    private String iso6391Code;
    private String languageFamily;

}
