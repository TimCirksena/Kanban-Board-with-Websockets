package hsos.vts.boundary.view;

import hsos.vts.boundary.acl.PostListeDTO;
import hsos.vts.boundary.websockets.AllBoardsWebsocket;
import hsos.vts.boundary.websockets.SingleBoardWebsocket;
import hsos.vts.entity.BoardKanbanCatalog;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/kanban")
@ApplicationScoped
@Produces(MediaType.TEXT_HTML)
@Consumes(MediaType.APPLICATION_JSON)
public class BoardKanbanResource {
    final static String ADMIN_ROLE = "admin";
    final static String USER_ROLE = "kunde";
    @Inject
    BoardKanbanCatalog boardKanbanCatalog;

    @Inject
    AllBoardsWebsocket allBoardsWebsocket;

    @Inject
    SingleBoardWebsocket singleBoardWebsocket;

    @Inject
    Template allBoards_view;

    @GET
    @RolesAllowed({ADMIN_ROLE,USER_ROLE})
    @Transactional
    public TemplateInstance getBoardKanbans(){
        return allBoards_view.data("boards", boardKanbanCatalog.getAllKanbanBoards());
    }
    @POST
    @Transactional
    @RolesAllowed({ADMIN_ROLE,USER_ROLE})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewBoardKanban(String titel){
        System.out.println(titel);
        allBoardsWebsocket.kanbanBoardCreated(boardKanbanCatalog.createBoard(titel));
        return Response.ok().build();
    }

    @DELETE
    @Transactional
    @RolesAllowed({ADMIN_ROLE,USER_ROLE})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBoard(long boardId){
        allBoardsWebsocket.kanbanBoardDelete(boardKanbanCatalog.deleteKanbanBoardById(boardId));
        return Response.ok().build();
    }
}

