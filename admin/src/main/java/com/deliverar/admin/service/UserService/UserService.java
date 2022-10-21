package com.deliverar.admin.service.UserService;

import com.deliverar.admin.model.dto.User.RoleResponse;
import com.deliverar.admin.model.dto.User.UserResponse;
import com.deliverar.admin.model.entity.Role;
import com.deliverar.admin.model.entity.User;

import java.util.List;

public interface UserService {

    UserResponse saveUser(User user);
    RoleResponse saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<UserResponse> getUsers();
}
