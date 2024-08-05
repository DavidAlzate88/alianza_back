package com.dhoko.alianza_back.controller;

import com.dhoko.alianza_back.entity.Client;
import com.dhoko.alianza_back.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientRepository clientRepository;


    @Test
    public void testListClients() throws Exception {
        List<Client> clients = Arrays.asList(
                new Client("jgutierrez", "Juliana Gutierrez", "jgutierrez@gmail.com", "20/05/2019", "20/05/2019"),
                new Client("mmartinez", "Manuel Martinez", "mmartinez@gmail.com", "20/05/2019", "20/05/2019")
        );
        when(clientRepository.findAll()).thenReturn(clients);

        mockMvc.perform(get("/api/clients"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",
                        hasSize(2)))
                .andExpect(jsonPath("$[0].key",
                        is("jgutierrez")));
    }

    @Test
    public void testGetClient() throws Exception {
        Client client = new Client("jgutierrez", "Juliana Gutierrez", "jgutierrez@gmail.com", "20/05/2019", "20/05/2019");
        when(clientRepository.findById("jgutierrez")).thenReturn(Optional.of(client));

        mockMvc.perform(get("/api/clients/jgutierrez"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.key", is("jgutierrez")));
    }

    @Test
    public void testCreateClient() throws Exception {
        Client newClient = new Client("newclient", "New Client", "newclient@gmail.com", "01/01/2024", "01/01/2024");
        when(clientRepository.save(any(Client.class))).thenReturn(newClient);

        mockMvc.perform(post("/api/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"key\":\"newclient\",\"id\":\"New Client\",\"email\":\"newclient@gmail.com\",\"startDate\":\"20/05/2019\",\"endDate\":\"01/01/2024\"}")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.key", is("newclient")));
    }
}
