package com.bytmasoft.dss.entities;

import com.bytmasoft.common.entities.BaseEntity;
import com.bytmasoft.common.enums.Gender;
import com.bytmasoft.common.enums.GenderConverter;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends BaseEntity implements Serializable {

    @Column(nullable = false)
    private Long schoolId;

    private String firstname;
    private String lastname;

    @Column(nullable = false)
    @Convert(converter = GenderConverter.class)
    private Gender gender;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Builder.Default
    @Column(columnDefinition = "Boolean default false")
    private boolean deleted = false;


}
