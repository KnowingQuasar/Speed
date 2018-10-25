package model;

import java.util.*;

public class Board {
    private ArrayList<Card> allCards = new ArrayList<>();
    private ArrayList<ArrayList<Card>> faceUpCards = new ArrayList<>();
    private ArrayList<Card> sides = new ArrayList<>();
    private ArrayList<ArrayList<Card>> decks = new ArrayList<>();
    private ArrayList<ArrayList<Card>> hands = new ArrayList<>();
    private int[] remaining = new int[2];
    private boolean stalemate = false;

    public Board() {
        decks.add(new ArrayList<>());
        decks.add(new ArrayList<>());
        hands.add(new ArrayList<>());
        hands.add(new ArrayList<>());
        faceUpCards.add(new ArrayList<>());
        faceUpCards.add(new ArrayList<>());

        initCards();
        for (int i = 0; i < 2; i++) {
            faceUpCards.get(i).add(allCards.get(i));
            remaining[i] = 20;
        }
        int i = 2;
        for (; i < 12; i++) {
            sides.add(allCards.get(i));
        }
        for (; i < 27; i++) {
            decks.get(0).add(allCards.get(i));
        }

        for (; i < 42; i++) {
            decks.get(1).add(allCards.get(i));
        }

        for (; i < 47; i++) {
            hands.get(0).add(allCards.get(i));
        }

        for (; i < 52; i++) {
            hands.get(1).add(allCards.get(i));
        }
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

    private boolean update(Card c, int pl) {
        if (!hands.get(pl).remove(c))
            return true;
        Card nc = decks.get(pl).get(0);
        hands.get(pl).add(nc);
        decks.get(pl).remove(nc);
        remaining[pl]--;
        return false;
    }

    private boolean canBePlaced(Card fc, Card c) {
        int fcval = fc.getValue();
        int cval = c.getValue();
        return (fcval - 1 == cval || fcval + 1 == cval) || (fcval == 13 && cval == 1)
                || (fcval == 1 && cval == 13);
    }

    private void recycle() {
        if (sides.size() == 0) {
            sides.clear();
            Collections.shuffle(faceUpCards.get(0));
            Collections.shuffle(faceUpCards.get(1));
            sides.addAll(faceUpCards.get(0));
            sides.addAll(faceUpCards.get(1));
        }
        faceUpCards.get(0).add(0, sides.get(0));
        sides.remove(0);
        faceUpCards.get(1).add(0, sides.get(0));
        sides.remove(0);
    }

    private ArrayList<Card> getHand(int pl) {
        return hands.get(pl);
    }

    public int getRemaining(int pl) {
        return remaining[pl];
    }

    public boolean resetStalemate(){
        if(!stalemate)
            return false;
        stalemate = false;
        return true;
    }

    public BoardState generateBoardState(int pl) {
        String hnd[] = new String[5];
        for (int i = 0; i < 5; i++) {
            hnd[i] = getHand(pl).get(i).toString();
        }
        String fc[] = {faceUpCards.get(0).get(0).toString(),
                faceUpCards.get(1).get(0).toString()};
        return new BoardState(fc, hnd, remaining);
    }

    public boolean placeCard(String card, int pl) throws IllegalArgumentException {
        Card c = new Card(card);
        if(c.getValue() == 0)
            return true;
        if (canBePlaced(faceUpCards.get(0).get(0), c)) {
            if(update(c, pl))
                return false;
            faceUpCards.get(0).add(0, c);
        } else if (canBePlaced(faceUpCards.get(0).get(0), c)) {
            if(update(c, pl))
                return false;
            faceUpCards.get(1).add(0, c);
        } else {
            return false;
        }

        return true;
    }

    public boolean updateStalemate() {
        if (stalemate) {
            recycle();
            stalemate = false;
            return true;
        }
        stalemate = true;
        return false;
    }
}