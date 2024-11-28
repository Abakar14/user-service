package com.bytmasoft.dss.entities;


import com.bytmasoft.common.entities.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Authority or Permission Table
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "permissions")
public class Permission extends BaseEntity implements Serializable {

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

   @Builder.Default
    @JsonIgnore
    @ManyToMany(mappedBy = "permissions", fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    @Builder.Default
    @Column(columnDefinition = "Boolean default false")
    private boolean deleted = false;


}
