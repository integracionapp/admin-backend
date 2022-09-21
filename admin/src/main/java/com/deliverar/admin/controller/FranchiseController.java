package com.deliverar.admin.controller;


import com.deliverar.admin.model.dto.Franchise.FranchiseRequest;
import com.deliverar.admin.model.dto.Franchise.FranchiseResponse;
import com.deliverar.admin.service.FranchiseService.FranchiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/franchise")
public class FranchiseController {

    @Autowired
    private FranchiseService franchiseService;

    @PostMapping("/")
    public ResponseEntity<FranchiseResponse> save(@RequestBody FranchiseRequest franchiseRequest){
        return new ResponseEntity<>(franchiseService.saveNewFranchise(franchiseRequest), HttpStatus.OK);
    }
}
