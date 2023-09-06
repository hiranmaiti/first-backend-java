package com.testing.testing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController


public class UserController {
    @Autowired
    
    private UserRepo userRepo;


    @GetMapping("/getuser")
    public List<User> getAllUser(){
        return userRepo.findAll();

    }

    // post new data
    @PostMapping("/postuser")
    public void  addAllEmployees(@RequestBody User user){
        userRepo.save(user);

    }

    @PutMapping("/{id}")
    public void Update_data(@PathVariable Long id, @RequestBody User Insert_value) {
        Optional<User> existing_data = userRepo.findById(id);
        if(existing_data.isPresent()) {
            User User_updated = existing_data.get();
            User_updated.setName(Insert_value.getName());
            User_updated.setEmail(Insert_value.getEmail());
            userRepo.save(User_updated);
        }
    }

    @DeleteMapping("/{id}")
    public void Delete_data(@PathVariable Long id) {
        userRepo.deleteById(id);
    }





}
