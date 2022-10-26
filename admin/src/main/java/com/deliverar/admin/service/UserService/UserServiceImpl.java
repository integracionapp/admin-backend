package com.deliverar.admin.service.UserService;

import com.deliverar.admin.mappers.UserMapper;
import com.deliverar.admin.model.dto.User.RoleRequest;
import com.deliverar.admin.model.dto.User.RoleResponse;
import com.deliverar.admin.model.dto.User.UserRequest;
import com.deliverar.admin.model.dto.User.UserResponse;
import com.deliverar.admin.model.entity.Role;
import com.deliverar.admin.model.entity.User;
import com.deliverar.admin.repository.RoleRepository;
import com.deliverar.admin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper = UserMapper.INSTANCE;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            });
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }
    }

    @Override
    public UserResponse saveUser(UserRequest userRequest) {
        log.info("Saving new user {} to the database", userRequest.getName());
        User user = userMapper.userRequestToUser(userRequest);
        Role role;
        for (String roleName : userRequest.getRoles()){
            role = roleRepository.findByName(roleName);
            user.getRoles().add(role);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.userToUserResponse(userRepository.save(user));
    }

    @Override
    public RoleResponse saveRole(RoleRequest roleRequest) {
        log.info("Saving new role {} to the database", roleRequest.getName());
        Role role = userMapper.roleRequestToRole(roleRequest);
        return userMapper.roleToRoleResponse(roleRepository.save(role));
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<UserResponse> getUsers() {
        log.info("Fetching all users");
        return userMapper.userToUserResponse(userRepository.findAll());
    }


}
