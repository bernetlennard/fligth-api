package com.teko.fligthapi.presentation.controller;

import com.teko.fligthapi.business.service.UserService;
import com.teko.fligthapi.presentation.dto.UserDto;
import com.teko.fligthapi.presentation.dto.UserRegistrationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*") // Wichtig für die SPA
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Neuen User registrieren
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserRegistrationDto request) {
        try {
            UserDto createdUser = userService.registerUser(request);
            return ResponseEntity.ok(createdUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Alle User abrufen (hilfreich fürs Testen)
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Einen bestimmten User abrufen
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}