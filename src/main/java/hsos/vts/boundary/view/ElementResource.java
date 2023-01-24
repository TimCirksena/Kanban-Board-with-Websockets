package hsos.vts.boundary.view;
import hsos.vts.boundary.acl.DeleteElementDTO;
import hsos.vts.boundary.acl.PostElementDTO;
import hsos.vts.boundary.acl.PostListeDTO;
import hsos.vts.boundary.websockets.SingleBoardWebsocket;
import hsos.vts.entity.BoardKanbanCatalog;
import hsos.vts.entity.ElementKanbanCatalog;
import hsos.vts.entity.ListeKanbanCatalog;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

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
public class ElementResource {

    @Inject
    SingleBoardWebsocket singleBoardWebsocket;
    @Inject
    ListeKanbanCatalog listeKanbanCatalog;

    @Inject
    BoardKanbanCatalog boardKanbanCatalog;

    @Inject
    ElementKanbanCatalog elementKanbanCatalog;

    @Inject
    Template elementCreate_view;


    @GET
    @Path("/{listeId}")
    @Transactional
    public TemplateInstance getElementCreate(@PathParam("listeId") long listeId){
        System.out.println("listeId check " + listeId);
        return elementCreate_view.data("listeId", listeId);
    }

    @POST
    @Transactional
    public Response addElementToList(PostElementDTO postElementDTO){
        singleBoardWebsocket.addElementToList(listeKanbanCatalog.addKanbanElement(postElementDTO));
        return Response.ok().build();
    }
    @DELETE
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/delete")
    public Response deleteElement(DeleteElementDTO deleteElementDTO) {
        singleBoardWebsocket.elementKanbanDelete(elementKanbanCatalog.deleteKanbanElementById(deleteElementDTO.elementId));
        return Response.ok().build();
    }

}
