package hsos.vts.boundary.rest;

import hsos.vts.boundary.acl.StubBoardDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/landingPage")
@ApplicationScoped
public class LandingPageWebSocket {
    List<Session> sessions = new ArrayList<>();

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
    public void onOpen(Session session) {
        sessions.add(session);

    }

    @OnMessage
    public void onMessage(String message) {
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

    }

    @OnError
    public void onError(Session session, Throwable throwable) {
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

