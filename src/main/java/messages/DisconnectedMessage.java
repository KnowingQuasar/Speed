package messages;

public class DisconnectedMessage extends Message{
    private int player;

    public DisconnectedMessage(int pl) {
        setAction("dc");
        player = pl;
    }
}
