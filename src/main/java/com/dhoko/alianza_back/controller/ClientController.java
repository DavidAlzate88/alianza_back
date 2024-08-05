package com.dhoko.alianza_back.controller;

import com.dhoko.alianza_back.entity.Client;
import com.dhoko.alianza_back.repository.ClientRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientRepository clientRepository;
    public ClientController(ClientRepository clienteRepository) {
        this.clientRepository = clienteRepository;

    }

    @GetMapping
    public List<Client> listClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/{key}")
    public Client getClient(@PathVariable String key) {
        return clientRepository.findById(key).orElse(null);
    }

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }
}
