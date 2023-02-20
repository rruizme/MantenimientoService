package com.nttdata.bc.resources;

import java.util.List;

import org.jboss.logging.Logger;

import com.nttdata.bc.models.Client;
import com.nttdata.bc.services.IClientService;

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

@Path("/clients")
public class ClienteResource {
    @Inject
    Logger logger;

    @Inject
    private IClientService service;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response insert(@Valid Client obj) {
        logger.info("[RRM] ingresa a insert expuesto");
        Client client = this.service.insert(obj);
        return Response.status(Status.CREATED).entity(client).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") Integer id, @Valid Client obj) {
        logger.info("Inicio ::: update ::: " + obj);
        obj.setClientId(id);
        Client client = this.service.update(obj);
        return Response.status(Status.CREATED).entity(client).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response fintAll() {
        List<Client> clients = this.service.findAll();
        return Response.ok(clients).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fintById(@PathParam("id") Integer id) {
        Client client = this.service.fintById(id);
        return Response.ok(client).build();
    }
}
