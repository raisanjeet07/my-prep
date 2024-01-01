package com.coding.whatsapp;

import com.coding.whatsapp.store.InMemoryKeyValueStore;
import com.san.lld.split_wise_lld.entity.User;

public class UserRepository {
    InMemoryKeyValueStore<String, User> userRepo = new InMemoryKeyValueStore<String, User>();

    public User getUserByPhoneNo(String phoneNo){
        return userRepo.getByKey(phoneNo);
    }

    public User adduser(User user){
        if(userRepo.getByKey(user.getMobile()) == null)
            userRepo.add(user.getMobile(), user);
        return user;
    }
}
