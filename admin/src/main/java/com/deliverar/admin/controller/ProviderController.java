package com.deliverar.admin.controller;

import com.deliverar.admin.model.dto.Provider.ProviderRequest;
import com.deliverar.admin.model.dto.Provider.ProviderResponse;
import com.deliverar.admin.service.ProviderService.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @PostMapping("/")
    public ResponseEntity<ProviderResponse> save(@RequestBody ProviderRequest providerRequest){
        return new ResponseEntity<>(providerService.saveNewProvider(providerRequest), HttpStatus.OK);
    }
}
