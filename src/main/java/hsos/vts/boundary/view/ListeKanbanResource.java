package hsos.vts.boundary.view;


import hsos.vts.entity.BoardKanbanCatalog;
import hsos.vts.entity.ListeKanbanCatalog;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/kanban/board")
@ApplicationScoped
@Produces(MediaType.TEXT_HTML)
@Consumes(MediaType.APPLICATION_JSON)
public class ListeKanbanResource {
    @Inject
    ListeKanbanCatalog listeKanbanCatalog;

    @Inject
    BoardKanbanCatalog boardKanbanCatalog;

    @Inject
    Template singleBoard_view;
    @Path("/{id}")
    @GET
    @Transactional
    public TemplateInstance getListsFromBoard(@PathParam("id") long kanbanId){
        return singleBoard_view.data("boards", boardKanbanCatalog.getKanbanBoardById(kanbanId).kanbanLists,
                "boardTitel", boardKanbanCatalog.getKanbanBoardById(kanbanId));
    }
}
