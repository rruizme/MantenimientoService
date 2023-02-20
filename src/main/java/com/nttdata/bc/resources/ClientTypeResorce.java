package com.nttdata.bc.resources;

import java.util.List;

import org.jboss.logging.Logger;

import com.nttdata.bc.models.ClientType;
import com.nttdata.bc.services.IClientTypeService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/client-types")
public class ClientTypeResorce {
    @Inject
    Logger logger;

    @Inject
    private IClientTypeService service;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response insert(@Valid ClientType obj) {
        ClientType clientType = this.service.insert(obj);
        return Response.status(Status.CREATED).entity(clientType).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") Integer id, @Valid ClientType obj) {
        logger.info("Inicio ::: update ::: " + obj);
        obj.setClientTypeId(id);
        ClientType clientType = this.service.update(obj);
        return Response.status(Status.CREATED).entity(clientType).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response fintAll() {
        List<ClientType> clientTypes = this.service.findAll();
        return Response.ok(clientTypes).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fintById(@PathParam("id") Integer id) {
        ClientType clientType = this.service.fintById(id);
        return Response.ok(clientType).build();
    }
}
