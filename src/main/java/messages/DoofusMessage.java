package messages;

public class DoofusMessage extends Message {
    private String card;

    public DoofusMessage(String card) {
        setAction("doofus");
        setCard(card);
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
}
