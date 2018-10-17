package websocket;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import com.google.gson.Gson;

import model.BoardState;

public class BoardStateDecoder implements Decoder.Text<BoardState> {

    private static Gson gson = new Gson();

    @Override
    public BoardState decode(String s) throws DecodeException {
        return gson.fromJson(s, BoardState.class);
    }

    @Override
    public boolean willDecode(String s) {
        return (s != null);
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
