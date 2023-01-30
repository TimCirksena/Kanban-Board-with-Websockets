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
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@ServerEndpoint("/kanban/board")
@ApplicationScoped
public class SingleBoardWebsocket {

    List<Session> sessions = new ArrayList<>();
    @Inject
    BoardKanbanCatalog boardKanbanCatalog;

    public void listeKanbanCreate(PostListeDTO postListeDTO){

        JsonObject typeHelper = new JsonObject();
        typeHelper.put("type", "liste_kanban_created");
        typeHelper.put("titel", postListeDTO.titel);
        typeHelper.put("color", postListeDTO.color);
        //Darf kein Obj sein weil er das nicht richtig checkt
        broadcast(typeHelper.toString());
    }
    public void listeKanbanDelete(DeleteListeDTO deleteListeDTO) {

            JsonObject typeHelper = new JsonObject();
            typeHelper.put("type", "liste_kanban_deleted");
            typeHelper.put("listeId", deleteListeDTO.listeId);
            typeHelper.put("boardId", deleteListeDTO.boardId);
            broadcast(typeHelper.toString());

    }
    public void elementKanbanDelete(Long elementId){
        JsonObject typeHelper = new JsonObject();
        typeHelper.put("type", "element_kanban_deleted");
        typeHelper.put("elementId", elementId);
        broadcast(typeHelper.toString());
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
    public void changeElementPos(ElementChangePosDTO elementChangePosDTO){
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("type","element_moved");
        jsonObject.put("elementId", elementChangePosDTO.elementId);
        jsonObject.put("listeId", elementChangePosDTO.listeId);
        broadcast(jsonObject.toString());
    }
    public void setColor(ColorDTO colorDTO){
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("type","color_changed");
        jsonObject.put("listeId", colorDTO.listeId);
        jsonObject.put("color", colorDTO.color);
        broadcast(jsonObject.toString());
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
