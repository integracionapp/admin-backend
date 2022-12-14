package com.deliverar.admin.repository;

import com.deliverar.admin.model.entity.Operator;
import com.deliverar.admin.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperatorRepository extends JpaRepository<Operator, Long> {

    List<Operator> findByLastNameContaining(String lastName);
    Operator findByLastName(String lastName);
    Operator findByUser(User user);

}
