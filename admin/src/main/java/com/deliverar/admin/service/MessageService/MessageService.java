package com.deliverar.admin.service.MessageService;

import com.deliverar.admin.model.dto.Message.ProviderMessage;

public interface MessageService {

    void sendMessageToQueue(Object message, String queue);
}
