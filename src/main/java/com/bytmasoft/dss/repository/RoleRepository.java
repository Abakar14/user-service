package com.bytmasoft.dss.repository;


import com.bytmasoft.common.repository.UtilRepository;
import com.bytmasoft.dss.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> , UtilRepository {
    Role findByName(String name);
}
