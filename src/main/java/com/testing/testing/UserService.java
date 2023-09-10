package com.testing.testing;

import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User login(String email, Integer password) {
        User user = userRepo.findByEmailAndPassword(email, password);
        return user;
    }
}
