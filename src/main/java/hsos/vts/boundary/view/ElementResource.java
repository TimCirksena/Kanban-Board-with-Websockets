package hsos.vts.boundary.view;
import hsos.vts.boundary.acl.PostListeDTO;
import hsos.vts.boundary.websockets.SingleBoardWebsocket;
import hsos.vts.entity.BoardKanbanCatalog;
import hsos.vts.entity.ElementKanbanCatalog;
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
    BoardKanbanCatalog boardKanbanCatalog;

    @Inject
    ElementKanbanCatalog elementKanbanCatalog;

    @Inject
    Template elementCreate_view;


    @GET
    @Transactional
    public TemplateInstance getElementCreate(@QueryParam("elementId") long elementId){
        return elementCreate_view.data("boards", elementKanbanCatalog.getElementById(elementId));
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/boardForList")
    public Response createNewListElement(PostListeDTO postListeDTO){
        singleBoardWebsocket.listeKanbanCreate(boardKanbanCatalog.addListToBoard(postListeDTO.boardId, postListeDTO.titel));
        return Response.ok().build();
    }
}
