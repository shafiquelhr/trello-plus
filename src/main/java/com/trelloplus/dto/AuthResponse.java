package com.trelloplus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

//This is the response backend will send if login is successful.

@Getter
@AllArgsConstructor
public class AuthResponse {
    private String token;
}
