package com.trelloplus.client;

import com.trelloplus.dto.LogEntryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "logger-service", url = "http://localhost:8081/")
public interface LogClient {

    @PostMapping("/logs")
    void sendLog(@RequestBody LogEntryDto logEntryDto);
}