package hsos.vts.boundary.rest;

import hsos.vts.boundary.acl.StubBoardDTO;
import hsos.vts.entity.BoardKanbanCatalog;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/kanban")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
public class BoardKanbanResource {
    //fd
    BoardKanbanCatalog boardKanbanCatalog;
    @Inject
    Template display;


    @Inject
    Template chat;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getChat(){
        return chat.instance();
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

