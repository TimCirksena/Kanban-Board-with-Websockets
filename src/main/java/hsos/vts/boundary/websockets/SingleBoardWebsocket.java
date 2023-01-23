package hsos.vts.boundary.websockets;

import hsos.vts.boundary.acl.*;
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
        broadcast(finalJson);
    }
    public void listeKanbanDelete(DeleteListeDTO deleteListeDTO) {

            JsonObject typeHelper = new JsonObject();
            typeHelper.put("type", "liste_kanban_deleted");
            typeHelper.put("listeId", deleteListeDTO.listeId);
            typeHelper.put("boardId", deleteListeDTO.boardId);
            String finalJson = typeHelper.toString();
            broadcast(finalJson);
    }
    public void addElementToList(StubElementDTO stubElementDTO){
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("type","element_added");
        jsonObject.put("listeId",stubElementDTO.listeId);
        jsonObject.put("elementId",stubElementDTO.elementId);
        jsonObject.put("titel",stubElementDTO.titel);
        broadcast(jsonObject.toString());
    }
    public void updateElement(FullElementDTO fullElementDTO){
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("type","element_edit");
        jsonObject.put("elementId",fullElementDTO.elementId);
        jsonObject.put("beschreibung",fullElementDTO.beschreibung);
        jsonObject.put("titel",fullElementDTO.titel);
        jsonObject.put("ersteller",fullElementDTO.ersteller);
        broadcast(jsonObject.toString());
    }


    /**
     * TODO: hier ist noch unklar wie genau das mit javascript dann geht
     * refreshen wir einfach nur die listen?
     * vielleicht alerts wenn was nicht klappt und dann reseten
     * */
    public void elementVonListeZuListeGetauscht(){

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

    private void broadcast(String json){
        for (Session session : sessions) {
            session.getAsyncRemote().sendText(json);
        }
    }
}
