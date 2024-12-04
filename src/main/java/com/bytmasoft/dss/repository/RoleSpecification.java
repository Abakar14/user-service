package com.bytmasoft.dss.repository;

import com.bytmasoft.dss.entities.Role;
import org.springframework.data.jpa.domain.Specification;

public class RoleSpecification {

    public static Specification<Role> hasActive(boolean active) {
        return (root, query, cb) -> {
            return   cb.equal(root.get("isActive"), active);
        };
    }
}
