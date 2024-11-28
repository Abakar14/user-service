package com.bytmasoft.dss.entities;

import com.bytmasoft.common.entities.BaseEntity;
import com.bytmasoft.dss.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;


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
    @Enumerated(EnumType.STRING)
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

/*    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();*/

    @Builder.Default
    @Column(columnDefinition = "Boolean default false")
    private boolean deleted = false;


}
