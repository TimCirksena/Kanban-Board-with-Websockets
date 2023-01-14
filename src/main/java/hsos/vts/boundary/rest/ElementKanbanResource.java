package hsos.vts.boundary.rest;

import hsos.vts.boundary.acl.FullElementDTO;
import hsos.vts.boundary.acl.KommentarDTO;
import hsos.vts.entity.ElementKanban;
import hsos.vts.entity.ElementKanbanCatalog;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Path("/element")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
public class ElementKanbanResource {

    @Inject
    private ElementKanbanCatalog elementKanbanCatalog;

    @GET
    @Transactional
    @Path("/elements/{id}")
    public Response getElementById(@PathParam("id") long elementId) {
        Optional<FullElementDTO> element = elementKanbanCatalog.getElementById(elementId);
        if (element.isPresent()) {
            return Response.ok(element.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Transactional
    @Path("/comments/{id}")
    public Response getCommentById(@PathParam("id") long commentId) {
        KommentarDTO comment = elementKanbanCatalog.getKommentarById(commentId);
        if (comment != null) {
            return Response.ok(comment).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Transactional
    @Path("/elements/{id}/comments")
    public Response getCommentsForElement(@PathParam("id") long elementId) {
        ArrayList<KommentarDTO> comments = elementKanbanCatalog.getKommentarFromKanbanElement(elementId);
        if (comments != null) {
            return Response.ok(comments).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Transactional
    @Path("/elements/{id}")
    public Response deleteElementById(@PathParam("id") long elementId) {
        boolean success = elementKanbanCatalog.deleteKanbanElementById(elementId);
        if (success) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Transactional
    @Path("/elements/{id}/title")
    public Response updateElementTitle(@PathParam("id") long elementId, String titel) {
        FullElementDTO updatedElement = elementKanbanCatalog.updateTitel(elementId, titel);
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
        FullElementDTO updatedElement = elementKanbanCatalog.updateBeschreibung(elementId, beschreibung);
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
        return Response.ok( elementKanbanCatalog.addElement(fullElementDTO.ersteller, fullElementDTO.titel, fullElementDTO.beschreibung)).build();
    }

    @GET
    @Transactional
    @Path("/elements")
    public Response getElements() {
        List<FullElementDTO> elementList = elementKanbanCatalog.getAllElements();
        return Response.ok(elementList).build();
    }
}
