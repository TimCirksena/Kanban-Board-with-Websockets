package hsos.vts.boundary.rest;

import hsos.vts.boundary.acl.StubBoardDTO;
import hsos.vts.entity.BoardKanban;
import hsos.vts.gateway.repo.BoardKanbanRepo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.Session;

@ServerEndpoint("/chat/{username}")
@ApplicationScoped
public class StartWebSocket {

    Map<String, Session> sessions = new ConcurrentHashMap<>();

    /*
        @OnOpen
        public void onOpen(Session session) {

            System.out.println("JUUUUUUUUUUUUUHU");
            // Send the entity to the client
            //session.getAsyncRemote().sendObject(boardKanban);
            sessions.put("hello",session);

        }
    */
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        sessions.put(username, session);
    }

    @OnMessage
    public void onMessage(String message, @PathParam("username") String username) {
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
            //Darf kein Obj sein, weil er das nicht richtig checkt
            sessions.get(username).getAsyncRemote().sendText(json);
        }
    }

    @OnClose
    public void onClose(Session session, @PathParam("username") String username) {

        sessions.remove(username);
        broadcast("User " + username + " left");
    }

    @OnError
    public void onError(Session session, @PathParam("username") String username, Throwable throwable) {
        sessions.remove(username);
        broadcast("User " + username + " left on error: " + throwable);
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
    private void broadcast(String message) {
        sessions.values().forEach(s -> {
            s.getAsyncRemote().sendObject(message, result -> {
                if (result.getException() != null) {
                    System.out.println("Unable to send message: " + result.getException());
                }
            });
        });
    }

}