package hsos.vts.boundary.view;
import hsos.vts.boundary.acl.DeleteElementDTO;
import hsos.vts.boundary.acl.PostElementDTO;
import hsos.vts.boundary.websockets.SingleBoardWebsocket;
import hsos.vts.control.elementKanban.ElementKanbanInterface;
import hsos.vts.control.listeKanban.ListeKanbanInterface;
import hsos.vts.entity.BoardKanbanCatalog;
import hsos.vts.entity.ListeKanbanCatalog;
import io.quarkus.qute.Template;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/kanban/create")
@ApplicationScoped
@Produces(MediaType.TEXT_HTML)
@Consumes(MediaType.APPLICATION_JSON)
public class ElementViewResource {

    final static String ADMIN_ROLE = "admin";
    final static String USER_ROLE = "kunde";
    @Inject
    SingleBoardWebsocket singleBoardWebsocket;
    @Inject
    ListeKanbanInterface listeKanbanInterface;

    @Inject
    ElementKanbanInterface elementKanbanInterface;

/*
    @GET
    @Path("/{listeId}")
    @RolesAllowed({ADMIN_ROLE,USER_ROLE})
    @Transactional
    public TemplateInstance getElementCreate(@PathParam("listeId") long listeId){
        System.out.println("listeId check " + listeId);
        return elementCreate_view.data("listeId", listeId);
    }
 */
    @POST
    @RolesAllowed({ADMIN_ROLE,USER_ROLE})
    @Transactional
    public Response addElementToList(PostElementDTO postElementDTO){
        singleBoardWebsocket.addElementToList(listeKanbanInterface.addKanbanElement(postElementDTO));
        return Response.ok().build();
    }
    @DELETE
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/delete")
    public Response deleteElement(DeleteElementDTO deleteElementDTO) {
        singleBoardWebsocket.elementKanbanDelete(elementKanbanInterface.deleteKanbanElementById(deleteElementDTO.elementId));
        return Response.ok().build();
    }

}
