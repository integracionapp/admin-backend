package com.deliverar.admin.controller;


import com.deliverar.admin.model.dto.ExceptionResponse;
import com.deliverar.admin.model.dto.Franchise.FranchiseRequest;
import com.deliverar.admin.model.dto.Franchise.FranchiseResponse;
import com.deliverar.admin.model.dto.Franchise.FranchiseUpdateRequest;
import com.deliverar.admin.model.dto.Operator.OperatorResponse;
import com.deliverar.admin.service.FranchiseService.FranchiseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/franchises")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operator found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FranchiseResponse.class))),
        @ApiResponse(responseCode = "404", description = "Operator not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
}

)
@Tag(name = "Franchise API", description = "Franchise API developed by Deliverar Administrator")
public class FranchiseController {

    @Autowired
    private FranchiseService franchiseService;

    @PostMapping("/")
    @Operation(summary = "Create a new Franchise", description = "Create a new Franchise")
    public ResponseEntity<FranchiseResponse> save(@RequestBody FranchiseRequest franchiseRequest){
        return new ResponseEntity<>(franchiseService.saveNewFranchise(franchiseRequest), HttpStatus.OK);
    }

    @PutMapping("/")
    @Operation(summary = "Update a Franchise", description = "Update a Franchise")
    public ResponseEntity<FranchiseResponse> update (@RequestBody FranchiseUpdateRequest franchiseUpdateRequest){
        return new ResponseEntity<>(franchiseService.update(franchiseUpdateRequest),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Franchise",description = "Delete a Franchise")
    public ResponseEntity deleteById(@PathVariable Long id){
        franchiseService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a Franchise by ID",description = "Find a Franchise by ID")
    public ResponseEntity<FranchiseResponse> getFranchiseById(@PathVariable Long id){
        return new ResponseEntity<>(franchiseService.getFranchiseResponseById(id),HttpStatus.OK);
    }

    @GetMapping("/")
    @Operation(summary = "Find all Franchises",description = "Find all Franchises")
    public ResponseEntity<List<FranchiseResponse>> getAllFranchises(){
        return new ResponseEntity<>(franchiseService.getAllFranchises(),HttpStatus.OK);
    }

    @GetMapping("/find/{name}")
    @Operation(summary = "Find a list of Franchises by name", description = "Find a list of Franchises by name")
    public ResponseEntity<List<FranchiseResponse>> getFranchisesByName(@PathVariable String name){
        return new ResponseEntity<>(franchiseService.getFranchisesByName(name), HttpStatus.OK);
    }


}
