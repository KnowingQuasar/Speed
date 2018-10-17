package websocket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import com.google.gson.Gson;

import model.BoardState;

public class BoardStateEncoder implements Encoder.Text<BoardState> {

    private static Gson gson = new Gson();

    @Override
    public String encode(BoardState bs) throws EncodeException {
        return gson.toJson(bs);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        // Custom initialization logic
    }

    @Override
    public void destroy() {
        // Close resources
    }
}
