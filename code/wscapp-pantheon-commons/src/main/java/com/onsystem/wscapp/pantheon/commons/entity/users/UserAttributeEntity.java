package com.onsystem.wscapp.pantheon.commons.entity.users;


import com.onsystem.wscapp.pantheon.commons.Constants;
import com.onsystem.wscapp.pantheon.commons.entity.applications.AttributeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
