package model;

import messages.BoardStateMessage;

import java.util.*;

public class Board {
    private ArrayList<Card> allCards;
    private Card[] faceUpCards;
    private ArrayList<Card> sides;
    private ArrayList<Card> deck1;
    private ArrayList<Card> deck2;
    private ArrayList<Card> hand1;
    private ArrayList<Card> hand2;
    private int p1remaining;
    private int p2remaining;

    public Board() {
        allCards = new ArrayList<>();
        faceUpCards = new Card[2];
        sides = new ArrayList<>();
        deck1 = new ArrayList<>();
        deck2 = new ArrayList<>();
        hand1 = new ArrayList<>();
        hand2 = new ArrayList<>();

        initCards();

        faceUpCards[0] = allCards.get(0);
        faceUpCards[1] = allCards.get(1);
        int i = 2;
        for (; i < 12; i++) {
            sides.add(allCards.get(i));
        }
        for (; i < 27; i++) {
            deck1.add(allCards.get(i));
        }

        for (; i < 42; i++) {
            deck2.add(allCards.get(i));
        }

        for (; i < 47; i++) {
            hand1.add(allCards.get(i));
        }

        for (; i < 52; i++) {
            hand2.add(allCards.get(i));
        }

        p1remaining = 20;
        p2remaining = 20;
    }

    private void initCards() {
        char sts[] = {'D', 'H', 'C', 'S'};
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 13; j++) {
                Card c = new Card(sts[i], j);
                allCards.add(c);
            }
        }
        Collections.shuffle(allCards);
    }

    public ArrayList<Card> getHand(int pl) {
        return pl == 0 ? hand1 : hand2;
    }

    public BoardState generateBoardState(int pl) {
        String hnd[] = new String[5];
        for (int i = 0; i < 5; i++) {
            hnd[i] = getHand(pl).get(i).toString();
        }
        String fc[] = {faceUpCards[0].toString(), faceUpCards[1].toString()};
        return new BoardState(fc, hnd, p1remaining, p2remaining);
    }
}
