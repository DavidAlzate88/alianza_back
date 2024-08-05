package com.dhoko.alianza_back.controller;

import com.dhoko.alianza_back.entity.Client;
import com.dhoko.alianza_back.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    Logger logger = LoggerFactory.getLogger(ClientController.class);
    private final ClientRepository clientRepository;
    public ClientController(ClientRepository clienteRepository) {
        this.clientRepository = clienteRepository;

    }

    @GetMapping
    public List<Client> listClients() {
        logger.info("getting a list of clients");
        return clientRepository.findAll();
    }

    @GetMapping("/{key}")
    public Client getClient(@PathVariable String key) {
        logger.info("getting a client by Id: {}", key);
        return clientRepository.findById(key).orElse(null);
    }

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        logger.info("creating a client: {}", client);
        return clientRepository.save(client);
    }
}
