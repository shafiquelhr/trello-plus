package com.trelloplus.client;

import com.trelloplus.dto.LogEntryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//feign client that will communicate with trello scheduler service

@FeignClient(name = "trello-scheduler-service", url = "http://localhost:8081/")
public interface TrelloSchedulerClient {

    @PostMapping("/logs")
    void sendLog(@RequestBody LogEntryDto logEntryDto);
}