package com.onsystem.wscapp.pantheon.output.api.dto.users;

import com.onsystem.wscapp.pantheon.output.api.dto.applications.ApplicationDataDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationWithUsersData {

    private ApplicationDataDTO applicationData;
    private List<UserDataInApplicationDTO> users;

}
