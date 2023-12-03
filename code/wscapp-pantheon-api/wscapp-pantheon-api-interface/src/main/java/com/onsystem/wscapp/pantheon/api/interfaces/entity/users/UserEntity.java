package com.onsystem.wscapp.pantheon.api.interfaces.entity.users;

import com.onsystem.wscapp.pantheon.api.dto.DeleteAuditFields;
import com.onsystem.wscapp.pantheon.api.dto.HightAuditFields;
import com.onsystem.wscapp.pantheon.api.interfaces.Constants;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.sql.Timestamp;

import static com.onsystem.wscapp.pantheon.api.interfaces.Constants.SCHEME_USERS;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = SCHEME_USERS, name = Constants.TABLE_USER,
        uniqueConstraints = @UniqueConstraint(columnNames = {"email", "login"}))
public class UserEntity implements HightAuditFields, DeleteAuditFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    @NotEmpty
    @Max(50)
    private String name;

    @NotEmpty
    @Max(50)
    private String surname;

    @NotEmpty
    @Max(100)
    @Column(unique = true)
    private String email;

    @NotEmpty
    @Max(30)
    @Column(unique = true)
    private String login;

    @NotEmpty
    @Max(255)
    private String password;

    @NotNull
    private Timestamp highDate;

    @Nullable
    @Positive
    private Integer highIdUser;


    @Nullable
    private Timestamp deleteDate;
    @Nullable
    private Integer deleteIdUser;
}
