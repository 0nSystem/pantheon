package com.onsystem.wscapp.pantheon.api.interfaces.entity.users;


import com.onsystem.wscapp.pantheon.api.interfaces.Constants;
import com.onsystem.wscapp.pantheon.api.interfaces.entity.applications.AttributeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = Constants.SCHEME_USERS, name = Constants.TABLE_USER_ATTRIBUTE)
@IdClass(UserAttributeKeyEntity.class)
public class UserAttributeEntity {

    @Id
    @OneToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;
    @Id
    @OneToOne
    @JoinColumn(name = "id_attribute")
    private AttributeEntity attribute;

    private String value;


}
