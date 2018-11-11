package messages;

public class PlaceMessage extends Message {
    private String card;
    private String midCard;

    public String getCard() {
        return card;
    }

    public String getMidCard() {
        return midCard;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public void setMidCard(String midCard) {
        this.midCard = midCard;
    }
}
