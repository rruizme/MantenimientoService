package com.nttdata.bc.services.impl;

import java.util.List;

import org.jboss.logging.Logger;

import com.nttdata.bc.exceptions.BadRequestException;
import com.nttdata.bc.exceptions.NotFoundException;
import com.nttdata.bc.models.Client;
import com.nttdata.bc.models.ClientType;
import com.nttdata.bc.repositories.ClientTypeRepository;
import com.nttdata.bc.repositories.ClienteRepository;
import com.nttdata.bc.services.IClientService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ClientServiceImpl implements IClientService {
    @Inject
    Logger logger;

    @Inject
    private ClienteRepository repository;

    @Inject
    private ClientTypeRepository clientTypeRepository;

    @Override
    public Client insert(Client obj) {
        logger.info("[RRM] ingresa a insert");
        this.validateData(obj);
        this.repository.persist(obj);
        return obj;
    }

    @Override
    public Client update(Client obj) {
        this.validateData(obj);

        Client client = this.repository.findById(obj.getClientId());
        if (client == null) {
            throw new NotFoundException("El cliente con id: " + obj.getClientId() + ", no existe.");
        }

        ClientType clientType = ClientType.builder()
                .clientTypeId(obj.getClientType().getClientTypeId()).build();

        client.setClientType(clientType);
        client.setDocumentIdentityType(obj.getDocumentIdentityType());
        client.setDocumentIdentity(obj.getDocumentIdentity());
        client.setName(obj.getName());
        client.setEmail(obj.getEmail());
        client.setPhone(obj.getPhone());

        return client;
    }

    @Override
    public List<Client> findAll() {
        return this.repository.listAll();
    }

    @Override
    public Client fintById(Integer id) {
        Client client = this.repository.findById(id);
        if (client == null) {
            throw new NotFoundException("El cliente con id: " + id.toString() + ", no existe.");
        }

        return this.repository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub

    }

    private void validateData(Client obj) {
        logger.info("[RRM] ingresa a validacion");
        if (obj.getClientType().getClientTypeId() == null) {
            logger.info("[RRM] falló validación 1");
            throw new BadRequestException("El campo clientType.clientTypeId es requerido.");
        }

        ClientType clientType = this.clientTypeRepository.findById(obj.getClientType().getClientTypeId());
        if (clientType == null) {
            logger.info("[RRM] falló validación 2");
            throw new NotFoundException(
                    "El tipo cliente con id: " + obj.getClientType().getClientTypeId() + ", no existe.");
        }

        if (clientType.getName().equals("Natural") && obj.getDocumentIdentityType().equals("RUC")) {
            logger.info("[RRM] falló validación 3");
            throw new BadRequestException("El campo documentIdentityType debe tener uno de estos valores: [DNI, CEX].");
        } else if (clientType.getName().equals("Jurídico") &&
                (obj.getDocumentIdentityType().equals("DNI") || obj.getDocumentIdentityType().equals("CEX"))) {
                    logger.info("[RRM] falló validación 4");
            throw new BadRequestException("El campo documentIdentityType debe tener uno de estos valores: [RUC].");
        }
    }
}
