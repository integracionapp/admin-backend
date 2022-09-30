package com.deliverar.admin.service.OperatorService;

import com.deliverar.admin.exceptions.OperatorNotFoundException;
import com.deliverar.admin.mappers.OperatorMapper;
import com.deliverar.admin.model.dto.Operator.OperatorRequest;
import com.deliverar.admin.model.dto.Operator.OperatorResponse;
import com.deliverar.admin.model.dto.Operator.OperatorUpdateRequest;
import com.deliverar.admin.model.entity.Operator;
import com.deliverar.admin.repository.OperatorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
public class OperatorServiceImpl implements OperatorService {

    @Autowired
    private OperatorRepository operatorRepository;

    private final OperatorMapper operatorMapper = OperatorMapper.INSTANCE;

    @Override
    public OperatorResponse save(OperatorRequest operatorRequest) {
        log.info("Saving new operator");
        Operator operator = operatorMapper.operatorRequestToOperator(operatorRequest);
        operator = operatorRepository.save(operator);
        log.info("New operator was saved succesfully");

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
}
