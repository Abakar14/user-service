package com.bytmasoft.dss.repository;


import com.bytmasoft.common.repository.UtilRepository;
import com.bytmasoft.dss.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>, UtilRepository {

    Optional<User> findByUsername(String username);

    @Query(value = "SELECT u FROM User u WHERE u.username=:usernameOrEmail Or u.email=:usernameOrEmail", nativeQuery = true)
    User findByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);

    @Query(value = "SELECT u FROM User u JOIN Users_roles ur ON u.id = ur.User_id WHERE ur.role_id=:role_id ", nativeQuery = true)
    List<User> findUsersByRoleId(@Param("role_id") long role_id);

    /**
     * @return
     */
    @Query(value = "SELECT u FROM User u JOIN users_roles ur ON u.id = ur.user_id join roles r on r.id = ur.role_id  WHERE r.name=:name ", nativeQuery = true)
    List<User> findStudentsByRoleName(@Param("name") long name);

    Optional<User> findByEmail(String email);
}
