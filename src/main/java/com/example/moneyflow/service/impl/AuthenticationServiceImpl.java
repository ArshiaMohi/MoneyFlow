package com.example.moneyflow.service.impl;

import com.example.moneyflow.dto.LoginRequest;
import com.example.moneyflow.dto.LoginResponse;
import com.example.moneyflow.dto.RegisterRequest;
import com.example.moneyflow.dto.UserResponse;
import com.example.moneyflow.entity.User;
import com.example.moneyflow.exception.EmailAlreadyExistsException;
import com.example.moneyflow.exception.InvalidPasswordException;
import com.example.moneyflow.exception.ResourceNotFoundException;
import com.example.moneyflow.repository.UserRepository;
import com.example.moneyflow.security.JwtService;
import com.example.moneyflow.service.interfaces.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public UserResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists.");
        }
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new InvalidPasswordException("Passwords do not match.");
        }
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        User savedUser = userRepository.save(user);

        return UserResponse.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new InvalidPasswordException("Email or Password is incorrect.");
        }

        String token = jwtService.generateToken(user);

        return LoginResponse.builder()
                .token(token)
                .build();
    }
}
