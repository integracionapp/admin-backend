package com.deliverar.admin.Operator;

import com.deliverar.admin.model.entity.Operator;
import com.deliverar.admin.repository.OperatorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class OperatorRepositoryTest {

    @Autowired
    private OperatorRepository operatorRepository;

    List<Operator> operators = new ArrayList<>();
    String lastName;

    @BeforeEach
    void setUp() {
        lastName = "Perez";
    }

    @Test
    void findByLastNameContaining() {
        operators = operatorRepository.findByLastNameContaining(lastName);
        for (Operator o: operators){
            assertTrue(o.getLastName().contains(lastName));
        }
    }
}