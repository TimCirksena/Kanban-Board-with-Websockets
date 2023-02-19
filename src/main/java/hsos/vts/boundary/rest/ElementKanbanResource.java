package hsos.vts.boundary.rest;

import hsos.vts.boundary.acl.FullElementDTO;
import hsos.vts.control.elementKanban.ElementKanbanInterface;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
@Path("/element")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
public class ElementKanbanResource {

    @Inject
    ElementKanbanInterface elementKanbanInterface;

    @GET
    @Transactional
    @Path("/elements/{id}")
    public Response getElementById(@PathParam("id") long elementId) {
        Optional<FullElementDTO> element = elementKanbanInterface.getElementById(elementId);
        if (element.isPresent()) {
            return Response.ok(element.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @PUT
    @Transactional
    @Path("/elements/{id}/title")
    public Response updateElementTitle(@PathParam("id") long elementId, String titel) {
        FullElementDTO updatedElement = elementKanbanInterface.updateTitel(elementId, titel);
        if (updatedElement != null) {
            return Response.ok(updatedElement).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    @PUT
    @Transactional
    @Path("/elements/{id}/description")
    public Response updateElementDescription(@PathParam("id") long elementId, String beschreibung) {
        FullElementDTO updatedElement = elementKanbanInterface.updateBeschreibung(elementId, beschreibung);
        if (updatedElement != null) {
            return Response.ok(updatedElement).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    @POST
    @Transactional
    @Path("/elements")
    public Response addElement(FullElementDTO fullElementDTO) {
        return Response.ok( elementKanbanInterface.addElement(fullElementDTO.ersteller, fullElementDTO.titel, fullElementDTO.beschreibung)).build();
    }

    @GET
    @Transactional
    @Path("/elements")
    public Response getElements() {
        List<FullElementDTO> elementList = elementKanbanInterface.getAllElements();
        return Response.ok(elementList).build();
    }
}
