package com.onsystem.wscapp.pantheon.api.request.language;

import com.onsystem.wscapp.pantheon.api.request.Constants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateLanguageRequest {


    @NotEmpty(message = Constants.ErrorValidationMessages.NOT_EMPTY)
    @Size(max = 10, message = Constants.ErrorValidationMessages.SIZE)
    private String name;

    @NotEmpty(message = Constants.ErrorValidationMessages.NOT_EMPTY)
    @Size(max = 50, message = Constants.ErrorValidationMessages.SIZE)
    private String iso6391Code;

    @NotEmpty(message = Constants.ErrorValidationMessages.NOT_EMPTY)
    @Size(max = 50, message = Constants.ErrorValidationMessages.SIZE)
    private String languageFamily;
}
