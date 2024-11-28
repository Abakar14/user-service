package com.bytmasoft.dss.repository;


import com.bytmasoft.common.repository.UtilRepository;
import com.bytmasoft.dss.entities.Role;
import com.bytmasoft.dss.entities.RoleAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Set;

public interface RoleAuthorityRepository  extends JpaRepository<RoleAuthority, Long>, JpaSpecificationExecutor<RoleAuthority>, UtilRepository {

	//Set<RoleAuthority> findByAuthority(String authority);
	//Set<RoleAuthority> findByAuthorityIn(Set<String> authority);
	//RoleAuthority findByAuthorityAndRole(String authority, Role role);
	//RoleAuthority findByAuthorityAndRoleAndAuthority(String authority, Role role, String authority2);
	Set<RoleAuthority> findByRole(Role role);
}
