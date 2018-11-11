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
    private int id;
    private boolean stalemateButtonClicked1;
    private boolean stalemateButtonClicked2;

    public Board() {
        decks.add(new ArrayList<Card>());
        decks.add(new ArrayList<Card>());
        hands.add(new ArrayList<Card>());
        hands.add(new ArrayList<Card>());
        faceUpCards.add(new ArrayList<Card>());
        faceUpCards.add(new ArrayList<Card>());

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
        if(remaining[pl] > 5) {
            Card nc = decks.get(pl).get(0);
            hands.get(pl).add(nc);
            decks.get(pl).remove(nc);
        }
        remaining[pl]--;
        return false;
    }

    private boolean canBePlaced(Card fc, Card c) {
        //face card value
        int fcval = fc.getValue();
        //card value
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
        if(!stalemateButtonClicked1 && !stalemateButtonClicked2)
            return false;
        stalemateButtonClicked2 = false;
        stalemateButtonClicked1 = false;
        return true;
    }

    public BoardState generateBoardState(int pl) {
        int sz = getHand(pl).size();
        String hnd[] = new String[sz];
        for (int i = 0; i < sz ; i++) {
            hnd[i] = getHand(pl).get(i).toString();
        }
        String fc[] = {faceUpCards.get(0).get(0).toString(),
                faceUpCards.get(1).get(0).toString()};
        return new BoardState(fc, hnd, new int[] {remaining[pl], remaining[pl == 0 ? 1 : 0]});
    }

    public void setId(int Id)
    {
        this.id = Id;
    }

    public boolean placeCard(String card, String midCard, int pl) throws IllegalArgumentException {
        Card c = new Card(card);
        Card m = new Card(midCard);
        if(c.getValue() == 0)
            return true;
        if (faceUpCards.get(0).get(0).equals(m) && canBePlaced(faceUpCards.get(0).get(0), c)) {
            if(update(c, pl))
                return false;
            faceUpCards.get(0).add(0, c);
        }
        else if (faceUpCards.get(1).get(0).equals(m) && canBePlaced(faceUpCards.get(1).get(0), c)) {
            if(update(c, pl))
                return false;
            faceUpCards.get(1).add(0, c);
        } else {
            return false;
        }

        return true;
    }

    public boolean updateStalemate() {

        if(id == 0 && stalemateButtonClicked1 == false)
        {
            stalemateButtonClicked1 = true;
            if(stalemateButtonClicked2){
                recycleCheck();
                return true;
            }
        }
        else if(id == 1 && stalemateButtonClicked2 == false)
        {
            stalemateButtonClicked2 = true;
            if(stalemateButtonClicked1){
                recycleCheck();
                return true;
            }
        }
        return false;
    }
    //THIS WAS COMPLAINING AT ME BECAUSE REASONS!
    private void recycleCheck()
    {
        recycle();
        stalemateButtonClicked2 = false;
        stalemateButtonClicked1 = false;
    }
}