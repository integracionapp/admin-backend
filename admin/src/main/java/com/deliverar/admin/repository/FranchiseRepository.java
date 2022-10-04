package com.deliverar.admin.repository;

import com.deliverar.admin.model.entity.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FranchiseRepository extends JpaRepository<Franchise,Long> {

    List<Franchise> findByNameContaining(String name);

}
