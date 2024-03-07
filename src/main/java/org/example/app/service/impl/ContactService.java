package org.example.app.service.impl;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.app.domain.contact.Contact;
import org.example.app.domain.contact.ContactResponse;
import org.example.app.domain.contact.ContactsResponse;
import org.example.app.repository.impl.ContactRepository;
import org.example.app.service.AppService;
import org.example.app.utils.ResponseMessage;

import java.util.List;
import java.util.Optional;

@Path("/api/v1/contacts")
@Produces({MediaType.APPLICATION_JSON})
public class ContactService implements AppService<Contact> {
    ContactRepository repository = new ContactRepository();

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(Contact contact) {
        repository.create(contact);
        Optional<Contact> optional = repository.getLastContact();
        ContactResponse response;
        if (optional.isPresent()) {
            response =
                    new ContactResponse(optional.get());
            return Response.ok(response.toString())
                    .status(Response.Status.CREATED).build();
        } else {
            return Response.ok(null)
                    .status(Response.Status.NO_CONTENT).build();
        }
    }

    @GET
    public Response fetchAll() {
        Optional<List<Contact>> optional = repository.fetchAll();
        ContactsResponse response;
        if (optional.isEmpty()) {
            return Response.noContent().build();
        } else {
            response = new ContactsResponse(optional.get());
            return Response.ok(response.toString()).build();
        }
    }

    @GET
    @Path("{id}")
    public Response fetchById(@PathParam("id") Long id) {
    Optional<Contact> optional = repository.fetchById(id);
        if (optional.isPresent()) {
        ContactResponse response =
                new ContactResponse(optional.get());
        return Response.ok(response.toString())
                .status(Response.Status.CREATED).build();
    } else {
        ResponseMessage resMsg =
                new ResponseMessage(404, false,
                        "Entity not found.");
        return Response.status(Response.Status.NOT_FOUND)
                .entity(resMsg.toString()).build();
    }
}

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response update(@PathParam("id") Long id, Contact contact) {
        Optional<Contact> optional = repository.fetchById(id);
        if (optional.isPresent()) {
            repository.update(id, contact);
            ContactResponse response =
                    new ContactResponse(optional.get());
            return Response.ok(response.toString())
                    .status(Response.Status.CREATED).build();
        } else {
            ResponseMessage respMsg =
                    new ResponseMessage(404, false,
                            "Entity not found.");
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(respMsg.toString()).build();
        }
    }
    @DELETE
    @Path("{id: [0-9]+}")
    public Response delete(@PathParam("id") Long id) {
        if (repository.isIdExists(id)) {
            repository.delete(id);
            ResponseMessage respMsg =
                    new ResponseMessage(200, true,
                            "Entity deleted.");
            return Response.ok(respMsg.toString()).build();
        } else {
            ResponseMessage respMsg =
                    new ResponseMessage(404, false,
                            "Entity not found.");
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(respMsg.toString()).build();
        }
    }
}