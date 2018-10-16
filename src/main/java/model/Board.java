package model;

import java.util.*;

public class Board {
    private ArrayList<Card> allCards;
    private Card[] faceUpCards;
    private ArrayList<Card> sides;
    private ArrayList<ArrayList<Card>> decks;
    private ArrayList<ArrayList<Card>> hands;
    private int p1remaining;
    private int p2remaining;

    public Board() {
        allCards = new ArrayList<>();
        faceUpCards = new Card[2];
        sides = new ArrayList<>();
        decks = new ArrayList<>(2);
        hands = new ArrayList<>(2);
        initCards();
        faceUpCards[0] = allCards.get(0);
        faceUpCards[1] = allCards.get(1);
        int i = 2;
        for (; i < 12; i++) {
            sides.add(allCards.get(i));
        }
        for (int k = 1; k <= 2; k++, i += 15) {
            for (; i < 12 + k * 15; i++) {
                decks.get(k).add(allCards.get(i));
            }
        }
        for (int k = 0; k < 2; k++, i += 5) {
            for (; i < 42 + k * 5; i++) {
                hands.get(k).add(allCards.get(i));
            }
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
        return hands.get(pl);
    }

    public BoardState generateBoard(int pl) {
        String hnd[] = new String[5];
        for (int i = 0; i < 5; i++) {
            hnd[i] = hands.get(pl).get(i).toString();
        }
        String fc[] = { faceUpCards[0].toString(), faceUpCards[1].toString() };
        return new BoardState(fc, hnd, p1remaining, p2remaining);
    }
}
