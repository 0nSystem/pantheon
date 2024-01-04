package com.onsystem.wscapp.pantheon.api.interfaces.entity.users;


import com.onsystem.wscapp.pantheon.api.interfaces.Constants;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.AttributeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = Constants.SCHEME_USERS, name = Constants.TABLE_USER_ATTRIBUTE)
public class UserAttributeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUserAttribute;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_attribute")
    private AttributeEntity attribute;

    @NotEmpty
    private String attribute_value;


}
