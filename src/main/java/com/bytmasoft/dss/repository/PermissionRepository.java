package com.bytmasoft.dss.repository;


import com.bytmasoft.common.repository.UtilRepository;
import com.bytmasoft.dss.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface PermissionRepository extends JpaRepository<Permission, Long>, JpaSpecificationExecutor<Permission> , UtilRepository {
        Permission findByName(String name);
}
