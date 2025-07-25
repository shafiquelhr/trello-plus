package com.trelloplus.service.impl;

import com.trelloplus.client.LogClient;
import com.trelloplus.dto.LogEntryDto;
import com.trelloplus.enums.LoggerApiEndpoints;
import com.trelloplus.exception.TicketNotFoundException;
import com.trelloplus.model.Ticket;
import com.trelloplus.repository.TicketRepository;
import com.trelloplus.rest.RestApiService;
import com.trelloplus.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final LogClient logClient; // Feign Client
    private final RestTemplate restTemplate; // inject RestTemplate
    private final RestApiService restApiService;

    //@Value("${logger.service.url}")
    //private String loggerServiceUrl; // set in application.properties

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, LogClient logClient, RestTemplate restTemplate, RestApiService restApiService) {
        this.ticketRepository = ticketRepository;
        this.logClient = logClient;
        this.restTemplate = restTemplate;
        this.restApiService = restApiService;
    }

    //HEADS-UP: MICROSERVICE COMM. HAPPENING BELOW!!!
    @Override
    public Ticket createTicket(Ticket ticket) {
        Ticket savedTicket = ticketRepository.save(ticket);

        LogEntryDto log = new LogEntryDto("TICKET_CREATED", savedTicket.getId(), savedTicket.getAssignedBy().getId(), LocalDateTime.now(), savedTicket.getAssignedBy().getName() + "created the Ticket.");

        //via the FeignClient
        //logClient.sendLog(log);

        // Send log to logger-service via REST Template
        String uri = LoggerApiEndpoints.LOGGER_URI.getUrl();
        String bearerToken = "Bear s4trsdfesdra#sdf.";
        LogEntryDto created = restApiService.post(uri, log, LogEntryDto.class, bearerToken);

        //restTemplate.postForObject(url, log, Void.class);

        return savedTicket;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException(id));
    }

}
