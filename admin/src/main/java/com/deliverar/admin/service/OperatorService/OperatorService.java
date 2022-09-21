package com.deliverar.admin.service.OperatorService;

import com.deliverar.admin.exceptions.OperatorNotFoundException;
import com.deliverar.admin.model.dto.Operator.OperatorRequest;
import com.deliverar.admin.model.dto.Operator.OperatorResponse;
import com.deliverar.admin.model.dto.Operator.OperatorUpdateRequest;
import com.deliverar.admin.model.entity.Operator;

import java.util.List;

public interface OperatorService {

    OperatorResponse save(OperatorRequest operatorRequest);

    OperatorResponse update(OperatorUpdateRequest operatorUpdateRequest);

    void delete(Long id);

    List<OperatorResponse> getAllOperators();

    Operator findById(Long id);

    OperatorResponse getOperatorResponseById(Long id) throws OperatorNotFoundException;
}
