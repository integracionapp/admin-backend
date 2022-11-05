package com.deliverar.admin.service.UserService;

import com.deliverar.admin.model.dto.Operator.OperatorResponse;
import com.deliverar.admin.model.dto.User.RoleRequest;
import com.deliverar.admin.model.dto.User.RoleResponse;
import com.deliverar.admin.model.dto.User.UserRequest;
import com.deliverar.admin.model.dto.User.UserResponse;
import com.deliverar.admin.model.entity.User;

import java.util.List;

public interface UserService {

    UserResponse saveUser(UserRequest user);
    RoleResponse saveRole(RoleRequest role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<UserResponse> getUsers();

    UserRequest createUserCredential(String role);

    boolean isUsernameAvailable(String username);
    void sendEmail(String email, String password, String user);
    User findById(Long id);

    OperatorResponse loginOperator(String token) throws Exception;
    User findByUsername(String username);

}
