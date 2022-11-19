package com.deliverar.admin.clients;

import com.deliverar.admin.model.dto.Message.ProviderMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "CoreClient", url = "http://core.deliver.ar")
public interface CoreClient {

    @PostMapping("/publicarMensaje")
    ResponseEntity sendMessageToQueue(@RequestParam String canal, @RequestBody Object message);
}
