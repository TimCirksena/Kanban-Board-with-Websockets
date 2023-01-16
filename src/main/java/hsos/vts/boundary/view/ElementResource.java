package hsos.vts.boundary.view;
import hsos.vts.entity.ElementKanbanCatalog;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/kanban/create")
@ApplicationScoped
@Produces(MediaType.TEXT_HTML)
@Consumes(MediaType.APPLICATION_JSON)
public class ElementResource {

    @Inject
    ElementKanbanCatalog elementKanbanCatalog;

    @Inject
    Template elementCreate_view;


    @GET
    @Transactional
    public TemplateInstance getElementCreate(@QueryParam("elementId") long elementId){
        return elementCreate_view.data("boards", elementKanbanCatalog.getElementById(elementId));
    }
}
