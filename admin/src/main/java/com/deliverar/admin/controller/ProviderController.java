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
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/provider")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Provider found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProviderResponse.class))),
        @ApiResponse(responseCode = "404", description = "Provider NOT found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
})
@Tag(name = "Provider API", description = "Provider API developed by Deliverar Administrator")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @PostMapping("/")
    @Operation(summary = "Create a new Provider", description = "Create a new Provider")
    public ResponseEntity<ProviderResponse> save(@Valid @RequestBody ProviderRequest providerRequest){
        return new ResponseEntity<>(providerService.saveNewProvider(providerRequest), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a provider by ID", description = "Find a provider by ID")
    public ResponseEntity<ProviderResponse> findProviderById(@PathVariable Long id) throws ProviderNotFoundException {
        return new ResponseEntity<>(providerService.getProviderResponseById(id), HttpStatus.OK);
    }

    @PutMapping("/")
    @Operation(summary = "Update a Provider", description = "Update an existing provider")
    public ResponseEntity<ProviderResponse> update(@Valid @RequestBody ProviderUpdateRequest providerRequest){
        return new ResponseEntity<>(providerService.update(providerRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a provider", description = "Delete an existing provider")
    public ResponseEntity deleteProviderById(@PathVariable Long id) {
        providerService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    @Operation(summary = "Get all the providers", description = "Get all the providers")
    public ResponseEntity<List<ProviderResponse>> getAllProviders(){
        return new ResponseEntity<>(providerService.getAllProviders(), HttpStatus.OK);
    }


}
