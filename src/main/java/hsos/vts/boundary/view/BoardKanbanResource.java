package hsos.vts.boundary.view;

import hsos.vts.boundary.acl.StubBoardDTO;
import hsos.vts.boundary.rest.BoardKanbanWebsocket;
import hsos.vts.entity.BoardKanbanCatalog;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

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
    //fd
    @Inject
    BoardKanbanCatalog boardKanbanCatalog;
    @Inject
    Template display;
    @Inject
    BoardKanbanWebsocket boardKanbanWebsocket;

    @Inject
    Template chat;

    @Inject
    Template kanbanBoards_view;

    @GET
    @Path("/chat")
    public TemplateInstance getChat(){
        return chat.instance();
    }

    @GET
    @Transactional
    public TemplateInstance getBoardKanbans(){
        return kanbanBoards_view.data("boards", boardKanbanCatalog.getAllKanbanBoards());
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewBoardKanban(String titel){
        System.out.println(titel);
        boardKanbanWebsocket.kanbanBoardCreated(boardKanbanCatalog.createBoard(titel));
        return Response.ok().build();
    }

    /*
        @GET
        @Transactional
        @Path("/allBoards")
        public Response getAllKanbanBoards() {
            return Response.ok(controller.getAllKunden()).build();
        }
    */
    /*
    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getEntity() {
        StubBoardDTO stubBoardDTO = new StubBoardDTO();
        stubBoardDTO.boardId = 1;
        stubBoardDTO.title = "test";
        return display.data("stubBoardDTO", stubBoardDTO);
    }
    */

}

