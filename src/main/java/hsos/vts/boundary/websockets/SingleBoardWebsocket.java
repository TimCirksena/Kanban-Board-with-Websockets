package hsos.vts.boundary.websockets;

import hsos.vts.boundary.acl.ListeKanbanDTO;
import hsos.vts.entity.BoardKanbanCatalog;
import io.vertx.core.json.JsonObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.websocket.Session;
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
        typeHelper.put("type", "list_kanban_created");
        String finalJson = typeHelper.toString();
        System.out.println(finalJson);
        //Darf kein Obj sein weil er das nicht richtig checkt
        for (Session session : sessions) {
            session.getAsyncRemote().sendText(finalJson);
        }
    }
}
