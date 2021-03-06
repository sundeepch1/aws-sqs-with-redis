package com.skc.sqs.controller;

import com.skc.sqs.model.User;
import com.skc.sqs.service.RedisService;
import com.skc.sqs.service.SqsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    SqsSender sqsSender;

    @Autowired
    RedisService redisService;

    @GetMapping
    public User getById(@PathParam("id") Integer id) {
        return redisService.getById(id);
    }

    @GetMapping("/all")
    public Map<Integer, User> findAll() {
        return redisService.findAll();
    }

    @PostMapping
    public boolean create(@RequestBody User user) {
        System.out.println("Creating User...");
        sqsSender.send(user);
        return true;
    }
}
