package hsos.vts.boundary.rest;

import hsos.vts.boundary.acl.StubBoardDTO;
import hsos.vts.entity.BoardKanbanCatalog;

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
public class BoardKanbanWebsocket {
    List<Session> sessions = new ArrayList<>();
    @Inject
    BoardKanbanCatalog boardKanbanCatalog;
    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);

    }

    @OnMessage
    public void onMessage(String message) {


        for (Session s: sessions){
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

    public void kanbanBoardCreated(StubBoardDTO boardDTO){
        Jsonb jsonb = JsonbBuilder.create();
        String json = jsonb.toJson(boardDTO);
        System.out.println(json);
        //Darf kein Obj sein weil er das nicht richtig checkt
        for (Session session : sessions) {
            session.getAsyncRemote().sendText(json);
        }
    }


    /*
        @OnMessage
        public void onMessage(String message) {
            System.out.println("Received message: " + message);
            BoardKanban boardKanban = new BoardKanban();
            boardKanban.setTitel("kaka");
            boardKanban.id = 1L;
            sessions.get("hello").getAsyncRemote().sendObject(boardKanban);
        }
    */
    /*
    private void broadcast(String message) {
        sessions.values().forEach(s -> {
            s.getAsyncRemote().sendObject(message, result -> {
                if (result.getException() != null) {
                    System.out.println("Unable to send message: " + result.getException());
                }
            });
        });
    }
     */

}

