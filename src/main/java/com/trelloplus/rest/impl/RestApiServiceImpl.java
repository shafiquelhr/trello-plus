package com.trelloplus.rest.impl;

import com.trelloplus.rest.RestApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestApiServiceImpl implements RestApiService {

    private final RestTemplate restTemplate;

    public RestApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public <T, R> R post(String uri, T requestBody, Class<R> responseType, String bearerToken) {
        HttpHeaders headers = createHeaders(bearerToken);
        HttpEntity<T> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<R> response = restTemplate.exchange(uri, HttpMethod.POST, entity, responseType);
        return response.getBody();
    }

    private HttpHeaders createHeaders(String bearerToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (bearerToken != null && !bearerToken.isEmpty()) {
            headers.setBearerAuth(bearerToken);
        }
        return headers;
    }

}
