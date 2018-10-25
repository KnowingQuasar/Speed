package model;

public class BoardState {
    private String fc[];
    private String hand [];

    private int remaining[];

    public BoardState(String[] fc, String[] hand, int[] remaining) {
        this.fc = fc;
        this.hand = hand;
        this.remaining = remaining;
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
