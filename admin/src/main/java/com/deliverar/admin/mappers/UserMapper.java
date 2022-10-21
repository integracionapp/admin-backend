package com.deliverar.admin.mappers;

import com.deliverar.admin.model.dto.User.RoleResponse;
import com.deliverar.admin.model.dto.User.UserResponse;
import com.deliverar.admin.model.entity.Role;
import com.deliverar.admin.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    List<UserResponse> userToUserResponse(List<User> users);
    UserResponse userToUserResponse(User user);
    RoleResponse roleToRoleResponse(Role role);

}
