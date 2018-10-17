package websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import model.*;

@ServerEndpoint(value = "/game", decoders = BoardStateDecoder.class, encoders = BoardStateEncoder.class)
public class ChatEndpoint {
    private Session session;
    private int id;

    private static final Set<ChatEndpoint> chatEndpoints = new CopyOnWriteArraySet<>();
    private static HashMap<String, ArrayList<Card>> users = new HashMap<>();
    private static boolean createGame = true;
    private static Board board;
    private static int numPlayers = 0;

    @OnOpen
    public void onOpen(Session session) throws IOException, EncodeException {
        if (numPlayers >= 2) {
            return;
        }
        if (createGame) {
            board = new Board();
            createGame = false;
        }
        this.session = session;
        chatEndpoints.add(this);
        id = numPlayers;
        users.put(session.getId(), board.getHand(0));
        numPlayers++;
        BoardState bs = board.generateBoard(0);
        send(bs);
    }

    @OnMessage
    public void onMessage(Session session, BoardState bs) throws IOException, EncodeException {
        broadcast(bs);
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        chatEndpoints.remove(this);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }

    private void send(BoardState bs) {
        try {
            this.session.getBasicRemote()
                    .sendObject(bs);
        } catch (IOException | EncodeException e){
            e.printStackTrace();
        }
    }

    private static void broadcast(BoardState bs) throws IOException, EncodeException {
        chatEndpoints.forEach(endpoint -> {
            synchronized (endpoint) {
                try {
                    endpoint.session.getBasicRemote()
                            .sendObject(bs);
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
