package com.bytmasoft.dss.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role_authority")
public class RoleAuthority implements Serializable {

private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;


	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

	@ManyToOne
	@JoinColumn(name = "auth_id", nullable = false)
	private Permission authority;

}
