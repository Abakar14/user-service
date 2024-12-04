package com.bytmasoft.dss.repository;

import com.bytmasoft.dss.entities.Permission;
import org.springframework.data.jpa.domain.Specification;

public class PermissionSpecification {

    public static Specification<Permission> hasActive(boolean active) {
        return (root, query, cb) -> {
            return   cb.equal(root.get("isActive"), active);
        };
    }
}
