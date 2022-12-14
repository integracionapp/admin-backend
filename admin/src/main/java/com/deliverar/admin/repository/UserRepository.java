package com.deliverar.admin.repository;

import com.deliverar.admin.model.entity.Role;
import com.deliverar.admin.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
    List<User> findByRoles(Role role);


}
