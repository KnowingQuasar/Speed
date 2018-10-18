package messages;

import model.BoardState;

public class BoardStateMessage extends Message {
    private String fc[];
    private String hand [];
    private int n1, n2;

    public BoardStateMessage(BoardState bs){
        setAction("bs");
        setFc(bs.getFc());
        setHand(bs.getHand());
        setN2(bs.getN2());
        setN1(bs.getN1());
    }

    public String[] getFc(){
        return fc;
    }

    public String[] getHand() {
        return hand;
    }

    public int getN1() {
        return n1;
    }

    public int getN2() {
        return n2;
    }

    public void setFc(String[] fc) {
        this.fc = fc;
    }

    public void setHand(String[] hand) {
        this.hand = hand;
    }

    public void setN1(int n1) {
        this.n1 = n1;
    }

    public void setN2(int n2) {
        this.n2 = n2;
    }
}
