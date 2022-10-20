package com.deliverar.admin.service.UserService;

import com.deliverar.admin.model.entity.Role;
import com.deliverar.admin.model.entity.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
}
