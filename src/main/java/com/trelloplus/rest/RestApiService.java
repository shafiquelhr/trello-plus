package com.trelloplus.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.net.http.HttpHeaders;

public interface RestApiService {

    public <T, R> R post(
            String uri,
            T requestBody,
            Class<R> responseType,
            String bearerToken
    );
}
