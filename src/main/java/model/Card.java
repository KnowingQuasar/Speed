package model;

public class Card {
    private char suit;
    private int value;

    @Override
    public String toString() {
        return suit + Integer.toString(value);
    }

    public char getSuit(){
        return suit;
    }

    public int getValue(){
        return value;
    }

    Card(char suit, int value)
    {
        this.suit = suit;
        this.value = value;
    }
}
