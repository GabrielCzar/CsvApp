package com.persistencia.csv.controller;

import com.persistencia.csv.model.User;
import com.persistencia.csv.util.CsvViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private CsvViewResolver csvViewResolver;

    @GetMapping
    public ResponseEntity<String> get() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAllUsers() {
        return new ResponseEntity<>(csvViewResolver.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@ModelAttribute("user") User user) {
        csvViewResolver.addUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
