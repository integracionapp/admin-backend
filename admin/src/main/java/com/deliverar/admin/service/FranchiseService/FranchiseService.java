package com.deliverar.admin.service.FranchiseService;

import com.deliverar.admin.model.dto.Franchise.FranchiseRequest;
import com.deliverar.admin.model.dto.Franchise.FranchiseResponse;
import com.deliverar.admin.model.dto.Franchise.FranchiseUpdateRequest;
import com.deliverar.admin.model.entity.Franchise;

public interface FranchiseService {

    FranchiseResponse saveNewFranchise(FranchiseRequest f);
    FranchiseResponse update(FranchiseUpdateRequest f);
    void deleteById(Long id);
    Franchise findById(Long id);
}
