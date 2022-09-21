package com.deliverar.admin.mappers;

import com.deliverar.admin.model.dto.Operator.OperatorRequest;
import com.deliverar.admin.model.dto.Operator.OperatorResponse;
import com.deliverar.admin.model.dto.Operator.OperatorUpdateRequest;
import com.deliverar.admin.model.entity.Operator;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OperatorMapper {

    OperatorMapper INSTANCE = Mappers.getMapper(OperatorMapper.class);

    Operator operatorRequestToOperator(OperatorRequest operatorRequest);

    OperatorResponse operatorToOperatorResponse(Operator operator);

    Operator operatorUpdateRequestToOperator(OperatorUpdateRequest operatorUpdateRequest);

    List<OperatorResponse> operatorToOperatorResponse(List<Operator> operators);

}
