package com.skc.sqs.service;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.skc.sqs.model.User;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class SqsSender {

    private QueueMessagingTemplate queueMessagingTemplate;

    public SqsSender(AmazonSQSAsync amazonSQSAsync){
        this.queueMessagingTemplate = new QueueMessagingTemplate(amazonSQSAsync);
    }

    public boolean send(User user) {
        System.out.println("Sending user..." + user.getName());
        this.queueMessagingTemplate.convertAndSend("user-cache-details", user);
        return true;
    }
}
