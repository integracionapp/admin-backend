package com.deliverar.admin.controller;

import com.deliverar.admin.exceptions.ProviderNotFoundException;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operator")
public class OperatorController {

    @Autowired
    private OperatorService operatorService;

    @PostMapping("/")
    public ResponseEntity<OperatorResponse> save(@RequestBody OperatorRequest operatorRequest) {
        return new ResponseEntity<>(operatorService.save(operatorRequest), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an operator by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Operator found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperatorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Operator NOT found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public ResponseEntity<OperatorResponse> findProviderById(@PathVariable Long id) throws ProviderNotFoundException {
        return new ResponseEntity<>(operatorService.getOperatorResponseById(id), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<OperatorResponse> update(@RequestBody OperatorUpdateRequest operatorUpdateRequest) {
        return new ResponseEntity<>(operatorService.update(operatorUpdateRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProviderById(@PathVariable Long id) {
        operatorService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<OperatorResponse>> getAllProviders() {
        return new ResponseEntity<>(operatorService.getAllOperators(), HttpStatus.OK);
    }
}
