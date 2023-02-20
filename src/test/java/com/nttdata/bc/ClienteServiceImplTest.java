package com.nttdata.bc;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.nttdata.bc.models.Client;
import com.nttdata.bc.models.ClientType;
import com.nttdata.bc.repositories.ClientTypeRepository;
import com.nttdata.bc.repositories.ClienteRepository;
import com.nttdata.bc.services.impl.ClientServiceImpl;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;

@QuarkusTest
public class ClienteServiceImplTest {

    @Inject
    ClientServiceImpl serviceImpl;

    @InjectMock
    ClienteRepository repository;

    @InjectMock
    ClientTypeRepository clientTypeRepository;

    Client client;
    ClientType clientType;
    ClientType clientTypeNotFound;
    List<Client> clients;

    @BeforeEach
    void setUp() {
        clientType = ClientType.builder()
                .clientTypeId(1)
                .name("Natural")
                .build();

        clientTypeNotFound = ClientType.builder()
                .clientTypeId(2)
                .name("Natural")
                .build();

        client = Client.builder()
                .clientId(1)
                .clientType(clientTypeNotFound)
                .name("william")
                .documentIdentity("DNI")
                .documentIdentity("88888888")
                .email("ing@gmail.com")
                .phone("999999999")
                .build();

        clients = new LinkedList<>();
        clients.add(client);
    }

    @Test
    public void testFintById() {
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(client);
        Client client = serviceImpl.fintById(1);

        Assertions.assertEquals("william", client.getName());
    }

    @Test
    public void testFindAll() {
        Mockito.when(repository.listAll()).thenReturn(clients);
        List<Client> clients = serviceImpl.findAll();

        Assertions.assertEquals(1, clients.size());
    }

}
