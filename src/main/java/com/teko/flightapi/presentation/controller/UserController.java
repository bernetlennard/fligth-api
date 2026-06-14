package com.teko.flightapi.presentation.controller;

import com.teko.flightapi.business.service.UserService;
import com.teko.flightapi.presentation.dto.UserDto;
import com.teko.flightapi.presentation.dto.UserRegistrationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
@Tag(name = "User", description = "Endpoints for user registration and profile management")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Register a new user", description = "Creates a new user account.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request or error during registration")
    })
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserRegistrationDto request) {
        try {
            UserDto createdUser = userService.registerUser(request);
            return ResponseEntity.ok(createdUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Get current user's profile", description = "Retrieves the profile of the currently authenticated user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user profile"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/me")
    public ResponseEntity<UserDto> getMe(Principal principal) {
        try {
            UserDto userDto = userService.getMe(principal.getName());
            return ResponseEntity.ok(userDto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}