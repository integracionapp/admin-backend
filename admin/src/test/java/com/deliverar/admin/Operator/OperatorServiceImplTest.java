package com.deliverar.admin.Operator;

import com.deliverar.admin.mappers.OperatorMapper;
import com.deliverar.admin.model.dto.Address.AddressRequest;
import com.deliverar.admin.model.dto.Address.AddressUpdateRequest;
import com.deliverar.admin.model.dto.Operator.OperatorRequest;
import com.deliverar.admin.model.dto.Operator.OperatorResponse;
import com.deliverar.admin.model.dto.Operator.OperatorUpdateRequest;
import com.deliverar.admin.model.entity.Operator;
import com.deliverar.admin.repository.OperatorRepository;
import com.deliverar.admin.service.OperatorService.OperatorServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class OperatorServiceImplTest {

    @Autowired
    private OperatorServiceImpl operatorService;

    @Autowired
    private OperatorRepository operatorRepository;

   private final OperatorMapper operatorMapper = OperatorMapper.INSTANCE;


    AddressRequest addressRequest;
    OperatorRequest operatorRequest;
    List<AddressRequest> addressRequests;

    OperatorUpdateRequest operatorUpdateRequest;
    AddressUpdateRequest addressUpdateRequest;
    List<AddressUpdateRequest> addressUpdateRequests;

    OperatorResponse operatorResponse;

    Integer variable;


    @BeforeEach
    void setUp() {
        variable = 0;
        addressRequests = new ArrayList<>();
        addressUpdateRequests = new ArrayList<>();
        operatorUpdateRequest = new OperatorUpdateRequest();

        addressRequest = AddressRequest.builder()
                .street("Benedetto")
                .number(4521)
                .latitude("52° 31' 28'' N")
                .longitude("13° 24' 38'' E")
                .zipCode("1365")
                .city("Escobar")
                .province("Buenos Aires")
                .build();

        addressRequests.add(addressRequest);

        operatorRequest = OperatorRequest.builder()
                .addresses(addressRequests)
                .dni(new BigInteger("42968472"))
                .phone("4268-0214")
                .firstName("Gonzalo")
                .lastName("Gonzales")
                .birthDate(LocalDateTime.now().withSecond(0))
                .registerDate(LocalDateTime.now().withSecond(0))
                .email("example@gmail.com")
                .gender("Female")
                .build();
        operatorResponse = operatorService.save(operatorRequest);
    }

    @AfterEach
    void tearDown() {
        if (variable == 0)
        operatorService.delete(operatorResponse.getId());
    }

    @Test
    void save() {
        OperatorResponse operatorFound = operatorMapper.operatorToOperatorResponse(operatorRepository.findByLastName(operatorRequest.getLastName()));
        //assertThat(operatorFound).usingRecursiveComparison().isEqualTo(operatorResponse);
        assertThat(operatorFound.getLastName()).isEqualTo(operatorResponse.getLastName());
    }

    @Test
    void update() {
        Operator operator = operatorRepository.findByLastName(operatorRequest.getLastName());
        addressRequest = operatorRequest.getAddresses().get(0);
        addressUpdateRequest = AddressUpdateRequest.builder()
                .city(addressRequest.getCity())
                .latitude(addressRequest.getLatitude())
                .longitude(addressRequest.getLongitude())
                .number(addressRequest.getNumber())
                .province(addressRequest.getProvince())
                .street(addressRequest.getStreet())
                .zipCode(addressRequest.getZipCode())
                .id(operator.getAddresses().get(0).getId())
                .build();
        addressUpdateRequests.add(addressUpdateRequest);

        operatorUpdateRequest = OperatorUpdateRequest.builder()
                .firstName(operator.getFirstName()+"Act")
                .lastName(operator.getLastName())
                .id(operator.getId())
                .dni(operator.getDni())
                .birthDate(operator.getBirthDate())
                .registerDate(operator.getRegisterDate())
                .phone(operator.getPhone())
                .email(operator.getEmail())
                .gender(operator.getEmail())
                .addresses(addressUpdateRequests)
                .build();

        operatorService.update(operatorUpdateRequest);
        operator = operatorRepository.findByLastName(operatorUpdateRequest.getLastName());
        assertThat(operator.getLastName()).isEqualTo(operatorUpdateRequest.getLastName());

    }

    @Test
    void delete() {
        operatorService.delete(operatorResponse.getId());
        List<OperatorResponse> operators = operatorService.getAllOperators();
        for(OperatorResponse o: operators){
            assertThat(o.getId()).isNotEqualTo(operatorResponse.getId());
        }
        variable = 1;
    }

    @Test
    void getAllOperators() {
        List<OperatorResponse> operatorsS = operatorService.getAllOperators();
        List<OperatorResponse> operatorsR = operatorMapper.operatorToOperatorResponse(operatorRepository.findAll());
        int x = 0;
        for (OperatorResponse o: operatorsR){
            assertThat(o.getId()).isEqualTo(operatorsR.get(x).getId());
            x++;
        }

    }

    @Test
    void findById() {
        Long longInteger = new Long(1L);
        Operator operator = operatorService.findById(longInteger);
        assertThat(operator.getId()).isEqualTo(longInteger);
    }

    @Test
    void getOperatorResponseById() {
        Long longInteger = new Long(1L);
        OperatorResponse operatorResponse = operatorService.getOperatorResponseById(longInteger);
        assertThat(operatorResponse.getId()).isEqualTo(longInteger);
    }

    @Test
    void getOperatorsByLastName() {
        String lastName = "Gonzales";
        List<OperatorResponse> operators = operatorService.getOperatorsByLastName(lastName);
        for(OperatorResponse o: operators){
            assertThat(o.getLastName(),containsString(lastName));
        }
    }
}