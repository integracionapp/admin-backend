package com.deliverar.admin.controller;

import com.deliverar.admin.exceptions.OperatorNotFoundException;
import com.deliverar.admin.model.dto.ExceptionResponse;
import com.deliverar.admin.model.dto.Operator.OperatorRequest;
import com.deliverar.admin.model.dto.Operator.OperatorResponse;
import com.deliverar.admin.model.dto.Operator.OperatorUpdateRequest;
import com.deliverar.admin.service.OperatorService.OperatorService;
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
@RequestMapping("/operators")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operator found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperatorResponse.class))),
        @ApiResponse(responseCode = "404", description = "Operator not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
})
@Tag(name = "Operator API", description = "Operator API developed by Deliver.ar Administrator")
public class OperatorController {

    @Autowired
    private OperatorService operatorService;

    @PostMapping("/")
    @Operation(summary = "Create a new Operator")
    public ResponseEntity<OperatorResponse> save(@RequestBody OperatorRequest operatorRequest) {
        return new ResponseEntity<>(operatorService.save(operatorRequest), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an Operator by ID")
    public ResponseEntity<OperatorResponse> findOperatorById(@PathVariable Long id) throws OperatorNotFoundException {
        return new ResponseEntity<>(operatorService.getOperatorResponseById(id), HttpStatus.OK);
    }

    @PutMapping("/")
    @Operation(summary = "Update an Operator")
    public ResponseEntity<OperatorResponse> update(@RequestBody OperatorUpdateRequest operatorUpdateRequest) {
        return new ResponseEntity<>(operatorService.update(operatorUpdateRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an Operator")
    public ResponseEntity deleteOperatorById(@PathVariable Long id) {
        operatorService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    @Operation(summary = "Get all Operators")
    public ResponseEntity<List<OperatorResponse>> getAllOperators() {
        return new ResponseEntity<>(operatorService.getAllOperators(), HttpStatus.OK);
    }
    @GetMapping("/find/{name}")
    @Operation(summary = "Get Operators by their last name", description = "Get Operators by last name")
    public ResponseEntity<List<OperatorResponse>> getOperatorsByLastName(@PathVariable String name){
        return new ResponseEntity<>(operatorService.getOperatorsByLastName(name),HttpStatus.OK);
    }
}
