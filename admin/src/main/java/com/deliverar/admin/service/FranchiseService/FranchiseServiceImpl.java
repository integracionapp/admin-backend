package com.deliverar.admin.service.FranchiseService;

import com.deliverar.admin.mappers.FranchiseMapper;
import com.deliverar.admin.mappers.ProviderMapper;
import com.deliverar.admin.model.dto.Franchise.FranchiseRequest;
import com.deliverar.admin.model.dto.Franchise.FranchiseResponse;
import com.deliverar.admin.model.entity.Franchise;
import com.deliverar.admin.repository.FranchiseRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class FranchiseServiceImpl implements FranchiseService{

    @Autowired
    private FranchiseRepository franchiseRepository;

    private final FranchiseMapper franchiseMapper = FranchiseMapper.INSTANCE;

    @Override
    public FranchiseResponse saveNewFranchise(FranchiseRequest f) {
        Franchise franchise = franchiseMapper.franchiseRequestToFranchise(f);
        franchise = franchiseRepository.save(franchise);
        return franchiseMapper.franchiseToFranchiseResponse(franchise);


    }
}
