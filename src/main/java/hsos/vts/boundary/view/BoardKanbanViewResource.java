package hsos.vts.boundary.view;

import hsos.vts.boundary.websockets.AllBoardsWebsocket;
import hsos.vts.boundary.websockets.SingleBoardWebsocket;
import hsos.vts.control.boardKanban.BoardKanbanInterface;
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
public class BoardKanbanViewResource {
    final static String ADMIN_ROLE = "admin";
    final static String USER_ROLE = "kunde";
    @Inject
    BoardKanbanInterface boardKanbanInterface;

    @Inject
    AllBoardsWebsocket allBoardsWebsocket;

    @Inject
    Template allBoards_view;

    @GET
    @RolesAllowed({ADMIN_ROLE,USER_ROLE})
    @Transactional
    public TemplateInstance getBoardKanbans(){

        return allBoards_view.data("boards", boardKanbanInterface.getAllKanbanBoards());
    }
    @POST
    @Transactional
    @RolesAllowed({ADMIN_ROLE,USER_ROLE})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewBoardKanban(String titel){
        allBoardsWebsocket.kanbanBoardCreated(boardKanbanInterface.createBoard(titel));
        return Response.ok().build();
    }

    @DELETE
    @Transactional
    @RolesAllowed({ADMIN_ROLE,USER_ROLE})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBoard(long boardId){
        allBoardsWebsocket.kanbanBoardDelete(boardKanbanInterface.deleteKanbanBoardById(boardId));
        return Response.ok().build();
    }
}

