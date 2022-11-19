package com.deliverar.admin.service.MessageService;

import com.deliverar.admin.clients.CoreClient;
import com.deliverar.admin.model.dto.Message.ProviderMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService{

    @Autowired
    private CoreClient coreClient;

    @Override
    public void sendMessageToQueue(Object message, String queue) {
        new Thread(() -> {
            log.info("Sending message to the Queue {}", queue);
            coreClient.sendMessageToQueue(queue,message);
        }).start();
    }
}
