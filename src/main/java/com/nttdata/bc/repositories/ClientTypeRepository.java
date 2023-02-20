package com.nttdata.bc.repositories;

import com.nttdata.bc.models.ClientType;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClientTypeRepository implements PanacheRepositoryBase<ClientType, Integer> {

}
