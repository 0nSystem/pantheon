package com.onsystem.wscapp.pantheon.commons.entity.spublic;



import com.onsystem.wscapp.pantheon.commons.Constants;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@Entity
@Table(schema = Constants.SCHEME_PUBLIC, name = Constants.TABLE_LANGUAGE)
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LanguageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLanguage;

    @NotEmpty
    @Max(100)
    private String name;

    @Column(name = "iso_639_1_code")
    @NotEmpty
    @Max(50)
    private String iso6391Code;

    @NotEmpty
    @Max(100)
    private String languageFamily;

}
