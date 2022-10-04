package com.deliverar.admin.service.FranchiseService;

import com.deliverar.admin.exceptions.FranchiseNotFoundException;
import com.deliverar.admin.mappers.FranchiseMapper;
import com.deliverar.admin.mappers.ProviderMapper;
import com.deliverar.admin.model.dto.Franchise.FranchiseRequest;
import com.deliverar.admin.model.dto.Franchise.FranchiseResponse;
import com.deliverar.admin.model.dto.Franchise.FranchiseUpdateRequest;
import com.deliverar.admin.model.entity.Franchise;
import com.deliverar.admin.repository.FranchiseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional //preguntar para que es
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



    @Override
    public FranchiseResponse update(FranchiseUpdateRequest f) {
    Franchise franchise = franchiseMapper.franchiseUpdateRequestToFranchise(f);
    log.info("id de franquicia" + franchise.getId() );
    franchise = franchiseRepository.save(franchise);
    return franchiseMapper.franchiseToFranchiseResponse(franchise);
    }

    @Override
    public void deleteById(Long id) {
        Franchise franchise = this.findById(id);
        franchiseRepository.delete(franchise);

        //porque no se usa esto? -> franchiseRepository.deleteById(id);
    }

    @Override
    public Franchise findById(Long id) {
        return franchiseRepository.findById(id)
                .orElseThrow(()-> new FranchiseNotFoundException("Franchise not found with ID "+ id));
    }

    @Override
    public FranchiseResponse getFranchiseResponseById(Long id) throws FranchiseNotFoundException {
        Franchise franchise = this.findById(id);
        return  franchiseMapper.franchiseToFranchiseResponse(franchise);
    }

    @Override
    public List<FranchiseResponse> getAllFranchises() {
        return franchiseMapper.franchiseToFranchiseResponse(franchiseRepository.findAll());
    }

    @Override
    public List<FranchiseResponse> getFranchisesByName(String name) {
        return franchiseMapper.franchiseToFranchiseResponse(franchiseRepository.findByNameContaining(name));
    }


}
