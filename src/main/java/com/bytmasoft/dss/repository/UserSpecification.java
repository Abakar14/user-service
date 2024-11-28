package com.bytmasoft.dss.repository;

import com.bytmasoft.dss.entities.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<User> hasActive(boolean active) {
        return (root, query, cb) -> {
            return   cb.equal(root.get("active"), active);
        };
    }
}
