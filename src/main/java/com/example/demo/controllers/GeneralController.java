package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class GeneralController {
    private static final Logger logger = LoggerFactory.getLogger(GeneralController.class);

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        logger.info("Performing POST /users");
        user.setCreatedDate(Optional.of(Date.from(LocalDateTime.now().atZone(ZoneId.of("America/Denver")).toInstant())));
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        logger.info("Performing PUT /users");
        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable UUID id) {
        logger.info("Performing GET /users/" + id);
        User returnedUser = userService.getUser(id);
        return ResponseEntity.ok(returnedUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        logger.info("Performing GET /users");
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id) {
        try {
            userService.deleteUser(id);
            logger.info("Performing DELETE /user/" + id);
        } catch (Exception e) {
            logger.error("Error performing DELETE /user/" + id);
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok("User Deleted");
    }
}
