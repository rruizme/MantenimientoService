package com.nttdata.bc.services.impl;

import java.util.List;

import org.jboss.logging.Logger;

import com.nttdata.bc.exceptions.NotFoundException;
import com.nttdata.bc.models.ClientType;
import com.nttdata.bc.repositories.ClientTypeRepository;
import com.nttdata.bc.services.IClientTypeService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ClientTypeServiceImpl implements IClientTypeService {
    @Inject
    Logger logger;

    @Inject
    private ClientTypeRepository repository;

    @Override
    public ClientType insert(ClientType obj) {
        this.repository.persist(obj);
        return obj;
    }

    @Override
    public ClientType update(ClientType obj) {
        ClientType clientType = this.repository.findById(obj.getClientTypeId());
        if (clientType == null) {
            throw new NotFoundException("El tipo cliente con id: " + obj.getClientTypeId() + ", no existe.");
        }

        clientType.setName(obj.getName());
        return clientType;
    }

    @Override
    public List<ClientType> findAll() {
        return this.repository.listAll();
    }

    @Override
    public ClientType fintById(Integer id) {
        ClientType clientType = this.repository.findById(id);
        if (clientType == null) {
            throw new NotFoundException("El tipo cliente con id: " + id.toString() + ", no existe.");
        }

        return this.repository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        ClientType obj = this.fintById(id);
        this.repository.persist(obj);
    }

}
