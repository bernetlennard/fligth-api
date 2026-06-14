package com.teko.flightapi.presentation.controller;

import com.teko.flightapi.business.mapper.UserMapper;
import com.teko.flightapi.persistence.dao.UserRepository;
import com.teko.flightapi.persistence.entity.User;
import com.teko.flightapi.presentation.dto.AuthRequestDto;
import com.teko.flightapi.presentation.dto.AuthResponseDto;
import com.teko.flightapi.security.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
@Tag(name = "Authentication", description = "Endpoints for user authentication")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
    }

    @Operation(summary = "User login", description = "Authenticates a user and returns a JWT token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authentication successful"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).build();
        }

        String token = jwtService.generateToken(user.getEmail());

        AuthResponseDto response = new AuthResponseDto();
        response.setToken(token);
        response.setUser(userMapper.toDto(user));

        return ResponseEntity.ok(response);
    }
}