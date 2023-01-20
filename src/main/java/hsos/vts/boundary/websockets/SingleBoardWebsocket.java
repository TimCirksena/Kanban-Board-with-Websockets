package hsos.vts.boundary.websockets;

import hsos.vts.boundary.acl.DeleteListeDTO;
import hsos.vts.boundary.acl.ListeKanbanDTO;
import hsos.vts.boundary.acl.StubBoardDTO;
import hsos.vts.entity.BoardKanbanCatalog;
import io.vertx.core.json.JsonObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.List;

@ServerEndpoint("/kanban/board")
@ApplicationScoped
public class SingleBoardWebsocket {

    List<Session> sessions = new ArrayList<>();
    @Inject
    BoardKanbanCatalog boardKanbanCatalog;

    public void listeKanbanCreate(ListeKanbanDTO listeKanbanDTO){
        Jsonb jsonb = JsonbBuilder.create();
        String json = jsonb.toJson(listeKanbanDTO);
        JsonObject typeHelper = new JsonObject(json);
        typeHelper.put("type", "liste_kanban_created");
        String finalJson = typeHelper.toString();
        System.out.println(finalJson);
        //Darf kein Obj sein weil er das nicht richtig checkt
        for (Session session : sessions) {
            session.getAsyncRemote().sendText(finalJson);
        }
    }
    public void listeKanbanDelete(DeleteListeDTO deleteListeDTO) {

            JsonObject typeHelper = new JsonObject();
            typeHelper.put("type", "liste_kanban_deleted");
            typeHelper.put("listeId", deleteListeDTO.listeId);
            typeHelper.put("boardId", deleteListeDTO.boardId);
            String finalJson = typeHelper.toString();
            for (Session session : sessions) {
                session.getAsyncRemote().sendText(finalJson);
            }
    }
    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }

    @OnMessage
    public void onMessage(String message) {
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
    }
}
