package model;

public class Message {
    private String action;
    private String card;

    @Override
    public String toString() {
        return super.toString();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
}
