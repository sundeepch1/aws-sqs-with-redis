package com.skc.sqs.repository;

import com.skc.sqs.model.User;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private RedisTemplate<String, User> redisTemplate;
    private HashOperations hashOperations;

    public UserRepositoryImpl(RedisTemplate<String, User> redisTemplate){
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(User user) {
        hashOperations.put("usercache", user.getId(), user);
    }

    @Override
    public Map<Integer, User> findAll() {
        return hashOperations.entries("usercache");
    }

    @Override
    public User findById(Integer id) {
        return (User) hashOperations.get("user-cache", id);
    }

    @Override
    public void update(User user) {
        save(user);
    }

    @Override
    public void delete(Integer id) {
        hashOperations.delete("usercache", id);
    }
}
