package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.MyAppUser;
import com.example.demo.Model.MyAppUserRepository;

@RestController
public class RegistrationController {

    @Autowired
    private MyAppUserRepository myAppUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(value = "/signup", consumes = "application/json")
    public ResponseEntity<String> createUser(@RequestBody MyAppUser user){

        MyAppUser existingAppUser = myAppUserRepository.findByEmail(user.getEmail());

        if(existingAppUser != null){
            if(existingAppUser.isVerified()){
                return new ResponseEntity<>("Dragon already exists and verified",HttpStatus.BAD_REQUEST);
            }else{
                String verificationToken = "738499";
                existingAppUser.setVerifcationToken(verificationToken);
                myAppUserRepository.save(existingAppUser);

                return new ResponseEntity<>("Dragon, check your inbox", HttpStatus.OK);
            }
        }

        System.out.println("Incoming user: " + user.getUsername());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String verificationToken = "738499";
        user.setVerifcationToken(verificationToken);

        myAppUserRepository.save(user);

        return new ResponseEntity<>("Dragon Registered! Verify your email", HttpStatus.OK);
    }
}
