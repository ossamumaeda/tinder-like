package com.maeda.tinder_like.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping(value = "user")
public class UserController {
    
    @GetMapping("/")        
    public ResponseEntity<Object> test(){

        return ResponseEntity.ok().body("Hello world");
    
    }

}
