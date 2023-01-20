package hsos.vts.boundary.view;


import hsos.vts.boundary.acl.DeleteListeDTO;
import hsos.vts.boundary.acl.PostListeDTO;
import hsos.vts.boundary.websockets.SingleBoardWebsocket;
import hsos.vts.entity.BoardKanbanCatalog;
import hsos.vts.entity.ListeKanbanCatalog;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/kanban/board")
@ApplicationScoped
@Produces(MediaType.TEXT_HTML)
@Consumes(MediaType.APPLICATION_JSON)
public class ListeKanbanResource {
    @Inject
    ListeKanbanCatalog listeKanbanCatalog;

    @Inject
    SingleBoardWebsocket singleBoardWebsocket;

    @Inject
    BoardKanbanCatalog boardKanbanCatalog;

    @Inject
    Template singleBoard_view;
    @Path("/{id}")
    @GET
    @Transactional
    public TemplateInstance getListsFromBoard(@PathParam("id") long kanbanId){
        return singleBoard_view.data("listeKanbans", boardKanbanCatalog.getKanbanBoardById(kanbanId).kanbanLists,
                "boardTitel", boardKanbanCatalog.getKanbanBoardById(kanbanId));
    }
    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/PostForList")
    public Response createNewListElement(PostListeDTO postListeDTO){
        singleBoardWebsocket.listeKanbanCreate(boardKanbanCatalog.addListToBoard(postListeDTO.boardId, postListeDTO.titel));
        return Response.ok().build();
    }
    @DELETE
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteListe(DeleteListeDTO deleteListeDTO){
        long id = listeKanbanCatalog.deleteListeKanbanById(deleteListeDTO.listeId, deleteListeDTO.boardId);
        if(0 < id) {
            singleBoardWebsocket.listeKanbanDelete(deleteListeDTO);
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
