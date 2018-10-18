package messages;

import model.BoardState;

public class BoardStateMessage extends Message {
    private String fc[];
    private String hand [];

    private int remaining[];

    public BoardStateMessage(BoardState bs){
        setAction("bs");
        setFc(bs.getFc());
        setHand(bs.getHand());
        setRemaining(bs.getRemaining());
    }

    public String[] getFc(){
        return fc;
    }

    public String[] getHand() {
        return hand;
    }

    public void setFc(String[] fc) {
        this.fc = fc;
    }

    public void setHand(String[] hand) {
        this.hand = hand;
    }

    public int[] getRemaining() {
        return remaining;
    }

    public void setRemaining(int[] remaining) {
        this.remaining = remaining;
    }
}
