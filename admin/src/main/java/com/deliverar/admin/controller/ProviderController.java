package com.deliverar.admin.controller;

import com.deliverar.admin.exceptions.ProviderNotFoundException;
import com.deliverar.admin.model.dto.ExceptionResponse;
import com.deliverar.admin.model.dto.Provider.ProviderRequest;
import com.deliverar.admin.model.dto.Provider.ProviderResponse;
import com.deliverar.admin.model.dto.Provider.ProviderUpdateRequest;
import com.deliverar.admin.service.ProviderService.ProviderService;
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
@RequestMapping("/provider")

public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @PostMapping("/")
    public ResponseEntity<ProviderResponse> save(@RequestBody ProviderRequest providerRequest){
        return new ResponseEntity<>(providerService.saveNewProvider(providerRequest), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a provider by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Provider found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProviderResponse.class))),
            @ApiResponse(responseCode = "404", description = "Provider NOT found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public ResponseEntity<ProviderResponse> findProviderById(@PathVariable Long id) throws ProviderNotFoundException {
        return new ResponseEntity<>(providerService.getProviderResponseById(id), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<ProviderResponse> update(@RequestBody ProviderUpdateRequest providerRequest){
        return new ResponseEntity<>(providerService.update(providerRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProviderById(@PathVariable Long id) {
        providerService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<ProviderResponse>> getAllProviders(){
        return new ResponseEntity<>(providerService.getAllProviders(), HttpStatus.OK);
    }


}
