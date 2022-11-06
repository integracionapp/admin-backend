package com.deliverar.admin.service.OperatorService;

import com.deliverar.admin.exceptions.OperatorNotFoundException;
import com.deliverar.admin.mappers.OperatorMapper;
import com.deliverar.admin.model.dto.Operator.OperatorRequest;
import com.deliverar.admin.model.dto.Operator.OperatorResponse;
import com.deliverar.admin.model.dto.Operator.OperatorUpdateRequest;
import com.deliverar.admin.model.dto.User.UserRequest;
import com.deliverar.admin.model.dto.User.UserResponse;
import com.deliverar.admin.model.entity.Operator;
import com.deliverar.admin.model.entity.User;
import com.deliverar.admin.repository.OperatorRepository;
import com.deliverar.admin.service.EmailService.EmailService;
import com.deliverar.admin.service.UserService.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
public class OperatorServiceImpl implements OperatorService {

    @Autowired
    private OperatorRepository operatorRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private final OperatorMapper operatorMapper = OperatorMapper.INSTANCE;


    @Override
    public OperatorResponse save(OperatorRequest operatorRequest) {
        log.info("Saving new operator");
        UserRequest userRequest = userService.createUserCredential("ROLE_OPERATOR");
        UserResponse userResponse = userService.saveUser(userRequest);
        User user = userService.findById(userResponse.getId());
        Operator operator = operatorMapper.operatorRequestToOperator(operatorRequest);
        operator.setUser(user);
        operator = operatorRepository.save(operator);
        log.info("New operator was saved succesfully");

        userService.sendEmail(operator.getEmail(), userRequest.getPassword(), user.getUsername());

        return operatorMapper.operatorToOperatorResponse(operator);
    }

    @Override
    public OperatorResponse update(OperatorUpdateRequest operatorUpdateRequest) {
        log.info("Updating operator with ID {}", operatorUpdateRequest.getId());
        Operator operatorEntity = operatorRepository.save(operatorMapper.operatorUpdateRequestToOperator(operatorUpdateRequest));
        log.info("Operator (ID {}) updated succesfully", operatorUpdateRequest.getId());
        return operatorMapper.operatorToOperatorResponse(operatorEntity);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting Operator with ID {}", id);
        Operator operator = this.findById(id);
        operatorRepository.delete(operator);
        log.info("Operator (ID {}) deleted successfully", id);
    }

    @Override
    public List<OperatorResponse> getAllOperators() {
        log.info("Getting all operators");
        return operatorMapper.operatorToOperatorResponse(operatorRepository.findAll());
    }

    @Override
    public Operator findById(Long id) {
        return operatorRepository.findById(id)
                .orElseThrow(() -> new OperatorNotFoundException("Operator not found with ID " + id));
    }

    @Override
    public OperatorResponse getOperatorResponseById(Long id) throws OperatorNotFoundException {
        log.info("Getting operator with ID {}", id);
        Operator operator = this.findById(id);
        log.info("Operator found with ID {}", id);
        return operatorMapper.operatorToOperatorResponse(operator);
    }

    @Override
    public List<OperatorResponse> getOperatorsByLastName(String lastName) {
        return operatorMapper.operatorToOperatorResponse(operatorRepository.findByLastNameContaining(lastName));
    }

    @Override
    public Operator findByUser(User user) {
        Operator operator = operatorRepository.findByUser(user);
        if (operator != null)
            return operator;

        throw new OperatorNotFoundException("Operator not found with user id -> "+user.getId());
    }
}
