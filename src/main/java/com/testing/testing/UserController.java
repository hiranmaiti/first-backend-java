package com.testing.testing;

import ch.qos.logback.core.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;


import java.lang.Object;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")


public class UserController {

    @Autowired
    
    private UserRepo userRepo;


    @Autowired
    private UserService userService;




    @GetMapping("/user")
    public List<User> getAllUser(){
        return userRepo.findAll();

    }

    @PostMapping("/usersignup")
    public void  addUser(@RequestBody User user){
        userRepo.save(user);

    }


//    @PostMapping("/userlogin")
//    public String login(@ModelAttribute("user") User user ) {
//
//        User oauthUser = userService.login(user.getEmail(), user.getPassword());
//
//
//        System.out.print(oauthUser);
//        if(Objects.nonNull(oauthUser))
//        {
//
//            return "Login Successful    "+user.getName();
//
//
//        } else {
//            return "Invalid Credential";
//
//
//        }
//
//
//
//    }



    @PostMapping("/userlogin")
    public ResponseEntity<String> login(@RequestBody User userLoginRequest) {
        try {
            // Validate userLoginRequest and perform login
            User oauthUser = userService.login(userLoginRequest.getEmail(), userLoginRequest.getPassword());

            if (oauthUser != null) {
                return ResponseEntity.ok("Login Successful: " + oauthUser.getName());
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
            }
        } catch (IncorrectResultSizeDataAccessException ex) {
            // Handle the case where the query didn't return a unique result
            // You can return an error message or log the issue
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed due to an internal error.");
        }
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
