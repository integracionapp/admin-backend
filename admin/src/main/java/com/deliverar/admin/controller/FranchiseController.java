package com.deliverar.admin.controller;


import com.deliverar.admin.model.dto.Franchise.FranchiseRequest;
import com.deliverar.admin.model.dto.Franchise.FranchiseResponse;
import com.deliverar.admin.model.dto.Franchise.FranchiseUpdateRequest;
import com.deliverar.admin.service.FranchiseService.FranchiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/franchise")
public class FranchiseController {

    @Autowired
    private FranchiseService franchiseService;

    @PostMapping("/")
    public ResponseEntity<FranchiseResponse> save(@RequestBody FranchiseRequest franchiseRequest){
        return new ResponseEntity<>(franchiseService.saveNewFranchise(franchiseRequest), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<FranchiseResponse> update (@RequestBody FranchiseUpdateRequest franchiseUpdateRequest){
        return new ResponseEntity<>(franchiseService.update(franchiseUpdateRequest),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        franchiseService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FranchiseResponse> getFranchiseById(@PathVariable Long id){
        return new ResponseEntity<>(franchiseService.getFranchiseResponseById(id),HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<FranchiseResponse>> getAllFranchises(){
        return new ResponseEntity<>(franchiseService.getAllFranchises(),HttpStatus.OK);
    }


}
