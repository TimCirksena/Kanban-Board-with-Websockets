package hsos.vts.boundary.websockets;

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

@ServerEndpoint("/kanban")
@ApplicationScoped
public class AllBoardsWebsocket {
    List<Session> sessions = new ArrayList<>();

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }

    @OnMessage
    public void onMessage(String message) {


        for (Session s : sessions) {
            s.getAsyncRemote().sendText("hi");
        }

        if (message.equalsIgnoreCase("ready")) {
            //broadcast("User " + username + " joined");

        } else {
            //broadcast(">> " + username + ": " + message);
            StubBoardDTO stubBoardDTO = new StubBoardDTO();
            stubBoardDTO.titel = "FUUUUUUUUCK";
            stubBoardDTO.boardId = 1L;
            Jsonb jsonb = JsonbBuilder.create();
            String json = jsonb.toJson(stubBoardDTO);
            System.out.println(json);
            //Darf kein Obj sein weil er das nicht richtig checkt
            for (Session session : sessions) {
                session.getAsyncRemote().sendText(json);
            }
        }
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
    }

    public void kanbanBoardCreated(StubBoardDTO boardDTO) {
        Jsonb jsonb = JsonbBuilder.create();
        String json = jsonb.toJson(boardDTO);
        JsonObject typeHelper = new JsonObject(json);
        typeHelper.put("type", "kanban_board_created");
        String finalJson = typeHelper.toString();
        System.out.println(finalJson);
        //Darf kein Obj sein weil er das nicht richtig checkt
        for (Session session : sessions) {
            session.getAsyncRemote().sendText(finalJson);
        }
    }

    public void kanbanBoardDelete(long boardId) {
        if(boardId > 0){
            JsonObject typeHelper = new JsonObject();
            typeHelper.put("type", "kanban_board_deleted");
            typeHelper.put("boardId", boardId);
            String finalJson = typeHelper.toString();
            for (Session session : sessions) {
                session.getAsyncRemote().sendText(finalJson);
            }
        }
        else{
            System.out.println("Fehler im Websocket bei delete");
        }
    }
}

