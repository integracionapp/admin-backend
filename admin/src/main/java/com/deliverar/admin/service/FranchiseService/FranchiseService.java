package com.deliverar.admin.service.FranchiseService;

import com.deliverar.admin.model.dto.Franchise.FranchiseRequest;
import com.deliverar.admin.model.dto.Franchise.FranchiseResponse;

public interface FranchiseService {

    FranchiseResponse saveNewFranchise(FranchiseRequest f);
}
