package com.lcwd.user.service.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    // create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    // single user get
    @GetMapping("/{id}")
    @CircuitBreaker(name = "getUserCircuitBreaker", fallbackMethod = "getUserCircuitBreakerFallback")
    public ResponseEntity<User> get(@PathVariable String id) {
        User user = userService.get(id);
        return ResponseEntity.ok(user);
    }

    // Fallback method for getUserCircuitBreaker
    public ResponseEntity<User> getUserCircuitBreakerFallback(String id, Exception ex) {
        logger.info("Fallback is executed because service is down", ex.getMessage());
        User user = new User("1111", "Dummy", "Dummy@dum.dum", "Dummy user is created because service is down.",
                new ArrayList<>());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // all user get
    @GetMapping
    @CircuitBreaker(name = "getAllUserCircuitBreaker", fallbackMethod = "getAllUserCircuitBreakerFallback")
    public ResponseEntity<List<User>> get() {
        List<User> users = userService.get();
        return ResponseEntity.ok(users);
    }

    // Fallback method for getAllUserCircuitBreaker
    public ResponseEntity<List<User>> getAllUserCircuitBreakerFallback(String id, Exception ex) {
        logger.info("Fallback is executed because service is down", ex.getMessage());
        return new ResponseEntity<>(new ArrayList<User>(), HttpStatus.OK);
    }

    // user update
    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user) {
        User updatedUser = userService.update(user);
        return ResponseEntity.ok(updatedUser);
    }

    // user delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.ok("Deleted user: " + id);
    }
}
