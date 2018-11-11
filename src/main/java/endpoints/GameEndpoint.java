package endpoints;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import coders.MessageDecoder;
import coders.MessageEncoder;
import messages.*;
import model.*;

@ServerEndpoint(value = "/game", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
public class GameEndpoint {
    private Session session;
    private int id;
    private boolean buttonCheck;

    private static final Set<GameEndpoint> gameEndpoints = new CopyOnWriteArraySet<>();
    private static boolean createGame = true;
    private static Board board;
    private static int numPlayers = 0;

    @OnOpen
    public void onOpen(Session session) {
        if (numPlayers > 1) {
//            id = 1;
             return;
        }
        if (createGame) {
            board = new Board();
            createGame = false;
        }
        this.session = session;
        gameEndpoints.add(this);
        id = numPlayers;
        numPlayers++;
        if(numPlayers == 2)
            broadcastBs();
    }

    @OnMessage
    public void onMessage(Message msg) {
        if (msg instanceof PlaceMessage) {
            if (!board.placeCard(((PlaceMessage) msg).getCard(),((PlaceMessage)msg).getMidCard(), id)) {
                send(new DoofusMessage(((PlaceMessage) msg).getCard()));
            }
            else{
                if (board.getRemaining(id) == 0) {
                    send(new WinMessage());
                    send(new LoseMessage(), id == 0 ? 1 : 0);
                    return;
                }
                if(board.resetStalemate())
                    send(new CloseStalemateMessage(), id == 0 ? 1 : 0);
                broadcastBs();
            }
        }
        else if(msg instanceof StalemateMessage) {
            if(id == 0) {
                board.setId(0);
            }
            else{
                board.setId(1);
            }
            if(board.updateStalemate()) {
                send(new CloseStalemateMessage(), id == 0 ? 1 : 0);
                broadcastBs();
            }
            else {
                send(new OpenStalemateMessage());
            }
        }
    }

    @OnClose
    public void onClose() {
        gameEndpoints.remove(this);
        broadcast(new DisconnectedMessage());
        numPlayers--;
    }

    @OnError
    public void onError(Throwable throwable) {
        if(throwable instanceof DecodeException) {
            send(new DoofusMessage("Invalid Card"));
            return;
        }
        onClose();
        throwable.printStackTrace();
    }

    private void send(Message msg) {
        try {
            this.session.getBasicRemote()
                    .sendObject(msg);
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }
    }

    private static void send(Message msg, int pl) {
        try {
            for (GameEndpoint endpoint : gameEndpoints) {
                if(endpoint.id == pl)
                    endpoint.session.getBasicRemote()
                            .sendObject(msg);
            }
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }
    }

    private static void broadcastBs() {
        for(GameEndpoint endpoint : gameEndpoints) {
            try {
                endpoint.session.getBasicRemote()
                        .sendObject(new BoardStateMessage(board.generateBoardState(endpoint.id)));
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        }
    }

    private static void broadcast(Message bs) {
        for (GameEndpoint endpoint : gameEndpoints) {
            try {
                endpoint.session.getBasicRemote()
                        .sendObject(bs);
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        }
    }
}
