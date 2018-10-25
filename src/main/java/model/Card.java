package model;

public class Card {
    private char suit;
    private int value;

    @Override
    public String toString() {
        return Integer.toString(value) + suit;
    }

    boolean equals(Card c) {
        return value == c.getValue() && suit == c.getSuit();
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

    Card(String rep) {
        value = Integer.parseInt(rep.substring(0, rep.length() - 1));
        suit = rep.charAt(rep.length() - 1);
    }
}
