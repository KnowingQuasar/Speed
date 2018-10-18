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

import coders.MessageDecoder;
import coders.MessageEncoder;
import messages.BoardStateMessage;
import messages.Message;
import model.*;

@ServerEndpoint(value = "/game", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
public class GameEndpoint {
    private Session session;
    private int id;

    private static final Set<GameEndpoint> gameEndpoints = new CopyOnWriteArraySet<>();
    private static HashMap<String, ArrayList<Card>> users = new HashMap<>();
    private static boolean createGame = true;
    private static Board board;
    private static int numPlayers = 0;

    @OnOpen
    public void onOpen(Session session) throws IOException, EncodeException {
        if (numPlayers >= 2) {
            id = 1;
           // return;
        }
        if (createGame) {
            board = new Board();
            createGame = false;
        }
        this.session = session;
        gameEndpoints.add(this);
        id = numPlayers;
        users.put(session.getId(), board.getHand(id));
        numPlayers++;
        BoardState bs = board.generateBoardState(id);
        send(new BoardStateMessage(bs));
    }

    @OnMessage
    public void onMessage(Session session, Message bs) throws IOException, EncodeException {
        broadcast(bs);
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        gameEndpoints.remove(this);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }

    private void send(BoardStateMessage bs) {
        try {
            this.session.getBasicRemote()
                    .sendObject(bs);
        } catch (IOException | EncodeException e){
            e.printStackTrace();
        }
    }

    private static void broadcast(Message bs) throws IOException, EncodeException {
        gameEndpoints.forEach(endpoint -> {
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
