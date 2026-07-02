package com.example.moneyflow.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private long id;
    private String name;
    private String email;
}
