package com.trelloplus.dto;

import lombok.Getter;
import lombok.Setter;

//This is the payload client (Postman, for now) will send in the login request body.

@Getter
@Setter
public class AuthRequest {
    private String username;
    private String password;
}
