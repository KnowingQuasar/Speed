package model;

import java.util.Objects;

public class Card {
    private char suit;
    private int value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit &&
                value == card.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, value);
    }

    @Override
    public String toString() {
        return Integer.toString(value) + suit;
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
