package com.testing.testing;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User saveDetails(User user) {
        return userRepo.save(user);
    }
}