package com.example.moneyflow.service.interfaces;

import com.example.moneyflow.dto.RegisterRequest;
import com.example.moneyflow.dto.UserResponse;

public interface AuthenticationService {
    UserResponse register(RegisterRequest request);
}
