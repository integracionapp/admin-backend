package com.deliverar.admin.service.UserService;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.deliverar.admin.exceptions.UsernameAlreadyExistException;
import com.deliverar.admin.mappers.OperatorMapper;
import com.deliverar.admin.mappers.UserMapper;
import com.deliverar.admin.model.dto.Message.AccountableMessage;
import com.deliverar.admin.model.dto.Operator.OperatorResponse;
import com.deliverar.admin.model.dto.User.RoleRequest;
import com.deliverar.admin.model.dto.User.RoleResponse;
import com.deliverar.admin.model.dto.User.UserRequest;
import com.deliverar.admin.model.dto.User.UserResponse;
import com.deliverar.admin.model.entity.Operator;
import com.deliverar.admin.model.entity.Role;
import com.deliverar.admin.model.entity.User;
import com.deliverar.admin.repository.OperatorRepository;
import com.deliverar.admin.repository.RoleRepository;
import com.deliverar.admin.repository.UserRepository;
import com.deliverar.admin.service.MessageService.MessageService;
import com.deliverar.admin.service.OperatorService.OperatorService;
import com.github.javafaker.Faker;
import com.deliverar.admin.service.EmailService.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Autowired
    private Faker faker;

    @Autowired
    private EmailService emailService;

    @Autowired
    private OperatorService operatorService;

    @Autowired
    private MessageService messageService;

    private final OperatorMapper operatorMapper = OperatorMapper.INSTANCE;

    @Value("${jwt.secret}")
    private String secret;


    @Autowired
    private OperatorRepository operatorRepository;

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
        boolean isAccountable = false;
        Role role;
        for (String roleName : userRequest.getRoles()){
            if (roleName.compareTo("ROLE_ACCOUNTABLE") == 0)
                isAccountable = true;

            role = roleRepository.findByName(roleName);
            user.getRoles().add(role);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (isAccountable){
            AccountableMessage message = AccountableMessage.builder()
                    .tipo("crearContable")
                    .nombre(user.getName())
                    .username(user.getUsername())
                    .password(userRequest.getPassword())
                    .build();
            messageService.sendMessageToQueue(message, "administrador");
        }

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

    @Override
    public UserRequest createUserCredential(String role) {

        String username = faker.funnyName().name();
        String password = faker.internet().password();
        String name = faker.name().fullName();
        while (!this.isUsernameAvailable(username))
            username = faker.funnyName().name();

        return UserRequest.builder()
                .name(name)
                .username(username)
                .password(password)
                .roles(Arrays.asList(role))
                .build();
    }

    @Override
    public void sendEmail(String email, String password, String user) {
        log.info("Sending Email to {}", email);
        emailService.sendEmail(email,"CUENTA CREADA CON EXITO","" +
                "<html>" +
                "<body>" +
                "   <div style='font-family:garamond'>" +
                "   <h1>??Bienvenido a Deliver.ar!</h1>" +
                "   <h2>Su cuenta ha sido creada con exito</h2>" +
                "<h3>"+
                "Le informamos que su cuenta fue creada exitosamente, sus credenciales son:"+
                "</h3>" +
                    "<ul>"+
                        "<li>"+
                            "<h3><u>Usuario</u>: " + user +"</h3>"+
                        "</li>"+
                        "<li>" +
                            "<h3><u>Contrase??a</u>: " + password +"</h3>"+
                            "</li> " +
                    "</ul>"+
                    "</p>"+
                "   <br> " +
                                    "<p>" +
                                    "Ya puede loguearse y usar los servicios de Deliver.ar. Tambi??n puede cambiar el usuario y contrase??a si lo desea." +
                        "</p>"+
                "<p><b>-Equipo de Administrador</b></p>" +
                "   </div>" +
                "</body>" +
                "</html>");
    }

    @Override
    public void sendEmailChangedPass(String email, String password) {
        emailService.sendEmail(email,"CONTRASE??A MODIFICADA CON EXITO","" +
               " <html>" +

                "<body>"+
    "<div style='font-family:garamond;'>" +
        "<h1>Deliver.ar</h1>"+
        "<h2>Su contrase??a ha sido modificada</h2>"+
        "<h3>" +
                "Le informamos que la contrase??a de su cuenta fue actualizada exitosamente, su nueva contrase??a es:" +
                "</h3>"+
        "<ul>"+
           "<li>"+
                "<h3><u>Contrase??a</u>:" + password +"</h3>"+
                "</li>"+
        "</ul>"+
        "<p>"+
                "Ya puede loguearse y usar los servicios de Deliver.ar. Tambi??n puede cambiar el usuario y contrase??a si lo desea." +
                "</p>" +
        "<p><b>-Equipo de Administrador</b></p>"+
    "</div>"+

"</body>"+

"</html>");
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id "+ id));
    }

    @Override
    public OperatorResponse loginOperator(String token) throws Exception {
        String username = this.getUsernameFromToken(token);
        User user = this.findByUsername(username);
        return operatorMapper.operatorToOperatorResponse(operatorService.findByUser(user));
    }

    @Override
    public UserResponse loginAdmin(String token) {
        String username = this.getUsernameFromToken(token);
        User user = this.findByUsername(username);
        Collection<Role> roles = user.getRoles();
        for (Role role : roles)
            if (role.getName().equals("ROLE_ADMIN"))
                return userMapper.userToUserResponse(user);

        throw new UsernameNotFoundException("Admin not found");
    }

    @Override
    public UserResponse loginAccountable(String token) {
        String username = this.getUsernameFromToken(token);
        User user = this.findByUsername(username);
        Collection<Role> roles = user.getRoles();
        for (Role role : roles)
            if (role.getName().equals("ROLE_ACCOUNTABLE"))
                return userMapper.userToUserResponse(user);

        throw new UsernameNotFoundException("Accountable not found");
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null)
            return user;

        throw new UsernameNotFoundException("User not found with username -> "+ username);
    }

    @Override
    public String getUsernameFromToken(String token) {
        String cleanToken = token.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(cleanToken);
        return decodedJWT.getSubject();
    }

    @Override
    public UserResponse forgotOperatorPassword(String username) {
        User user = userRepository.findByUsername(username);
        Operator operator = operatorRepository.findByUser(user);
        String pass = faker.internet().password();
        user.setPassword(passwordEncoder.encode(pass));

        this.sendEmailChangedPass(operator.getEmail(), pass);
        userRepository.save(user);

        return userMapper.userToUserResponse(user);
    }

    @Override
    public UserResponse updateOperatorPassword(String username, String currentPassword, String newPassword) {
        log.info("Updating password for the user -> {}", username);
        User user = this.findByUsername(username);
        if (!passwordEncoder.matches(currentPassword,user.getPassword()))
            throw new UsernameNotFoundException("Unmatched username and password");

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return userMapper.userToUserResponse(user);
    }


    @Override
    public boolean isUsernameAvailable(String username) {
        if (!userRepository.existsByUsername(username))
            return true;

        throw new UsernameAlreadyExistException("We have a user with username: "+username);
    }

    @Override
    public List<UserResponse> getUsersByRole(String role) {
        log.info("Getting all users with role = ".concat(role));
        Role roleAux = roleRepository.findByName(role);
        List<User> users = userRepository.findByRoles(roleAux);
        return userMapper.userToUserResponse(users);
    }
}
