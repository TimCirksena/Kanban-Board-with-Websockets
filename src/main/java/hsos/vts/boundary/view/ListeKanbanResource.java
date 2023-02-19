package hsos.vts.boundary.view;


import hsos.vts.boundary.acl.*;
import hsos.vts.boundary.websockets.SingleBoardWebsocket;
import hsos.vts.control.boardKanban.BoardKanbanInterface;
import hsos.vts.control.listeKanban.ListeKanbanInterface;
import hsos.vts.control.elementKanban.ElementKanbanInterface;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.vertx.core.json.JsonObject;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/kanban/board")
@ApplicationScoped
@Produces(MediaType.TEXT_HTML)
@Consumes(MediaType.APPLICATION_JSON)
public class ListeKanbanResource {
    final static String ADMIN_ROLE = "admin";
    final static String USER_ROLE = "kunde";
    @Inject
    ListeKanbanInterface listeKanbanInterface;

    @Inject
    SingleBoardWebsocket singleBoardWebsocket;

    @Inject
    BoardKanbanInterface boardKanbanInterface;

    @Inject
    ElementKanbanInterface elementKanbanInterface;

    @Inject
    Template singleBoard_view;

    @Path("/{id}")
    @GET
    @RolesAllowed({ADMIN_ROLE,USER_ROLE})
    @Transactional
    public TemplateInstance getListsFromBoard(@PathParam("id") long kanbanId) {
        return singleBoard_view.data("listeKanbans", boardKanbanInterface.getKanbanBoardById(kanbanId).kanbanLists,
                "boardTitel", boardKanbanInterface.getKanbanBoardById(kanbanId));
    }

    @GET
    @Transactional
    @RolesAllowed({ADMIN_ROLE,USER_ROLE})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/element/{elementId}")
    public Response getElement(@PathParam("elementId") long elementId) {
        if (elementKanbanInterface.getElementById(elementId).isPresent()) {
            FullElementDTO fullElementDTO = elementKanbanInterface.getElementById(elementId).get();
            return Response.ok(fullElementDTO).build();
        }
        return Response.serverError().build();
    }
    @GET
    @Transactional
    @RolesAllowed({ADMIN_ROLE,USER_ROLE})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/userName")
    public Response getUserName(@Context SecurityContext securityContext){
        String username = securityContext.getUserPrincipal().getName();
        System.out.println(username);

        JsonObject typeHelper = new JsonObject();
        typeHelper.put("username", username);
        return Response.ok(typeHelper.toString()).build();
    }

    @POST
    @Transactional
    @RolesAllowed({ADMIN_ROLE,USER_ROLE})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/PostForList")
    public Response createNewListElement(PostListeDTO postListeDTO) {
        singleBoardWebsocket.listeKanbanCreate(boardKanbanInterface.addListToBoard(postListeDTO.boardId, postListeDTO.titel,postListeDTO.color));
        return Response.ok().build();
    }

    @DELETE
    @Transactional
    @RolesAllowed({ADMIN_ROLE,USER_ROLE})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listeDelete")
    public Response deleteListe(DeleteListeDTO deleteListeDTO) {
        long id = listeKanbanInterface.deleteListeKanbanById(deleteListeDTO.listeId, deleteListeDTO.boardId);
        if (0 < id) {
            singleBoardWebsocket.listeKanbanDelete(deleteListeDTO);
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }


    @PATCH
    @Transactional
    @RolesAllowed({ADMIN_ROLE,USER_ROLE})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateElement(FullElementDTO fullElementDTO) {
        System.out.println("!!! " + fullElementDTO.elementId);
        singleBoardWebsocket.updateElement(elementKanbanInterface.updateElement(fullElementDTO));
        return Response.ok().build();
    }

    @PATCH
    @Transactional
    @RolesAllowed({ADMIN_ROLE,USER_ROLE})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/changePos")
    public Response elementChangePos(ElementChangePosDTO elementChangePosDTO) {
        singleBoardWebsocket.changeElementPos(listeKanbanInterface.moveFromListToList(elementChangePosDTO.listeId, elementChangePosDTO.elementId));
        return Response.ok().build();
    }

    @PATCH
    @Transactional
    @RolesAllowed({ADMIN_ROLE,USER_ROLE})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/setColor")
    public Response setColor(ColorDTO colorDTO){
        if(colorDTO != null){
            listeKanbanInterface.setColorFromList(colorDTO.listeId,colorDTO.color);
            singleBoardWebsocket.setColor(colorDTO);
            return Response.accepted().build();
        }
        return Response.noContent().build();
    }
}
