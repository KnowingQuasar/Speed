package coders;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import messages.*;
import java.io.StringReader;
import java.util.regex.Pattern;

public class MessageDecoder implements Decoder.Text<Message> {
    @Override
    public Message decode(String msg) throws DecodeException {
        Message message = null;
        if(willDecode(msg)) {
            JsonObject obj = Json.createReader(new StringReader(msg)).readObject();
            ObjectMapper mp = new ObjectMapper();

            String action = obj.getString("action");
            try{
                switch(action) {
                    case "place":
                        if(!Pattern.compile("\\A\\d?\\d\\D\\Z").matcher(obj.getString("card")).find())
                            throw new DecodeException(msg, "Invalid card value");
                        message = mp.readValue(msg, PlaceMessage.class);
                        break;
                    case "stalemate":
                        message = mp.readValue(msg, StalemateMessage.class);
                        break;
                }
            }
            catch (DecodeException de){
                throw de;
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return message;
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
