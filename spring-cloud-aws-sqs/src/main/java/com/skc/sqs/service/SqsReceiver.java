package com.skc.sqs.service;

import com.skc.sqs.model.User;
import com.skc.sqs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

@Service
public class SqsReceiver {

    @Autowired
    UserRepository userRepository;

    @SqsListener("user-cache-details")
    public void userCacheListener(User user){
        System.out.println("Receiving Message for user..." + user.getName());
        userRepository.save(user);
        System.out.println("Save Message in Cache");
    }

}
