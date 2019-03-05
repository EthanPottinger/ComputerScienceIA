package cardtoolkit;

import collections.LinkedList;

/**
 * Hand.java - 
 * 
 * @author Ethan Pottinger
 * @since 30-Nov-2018
 */
public class Hand {
    
    private LinkedList<Card> cards;
    public static Deck deck = new Deck();

    public Hand() {
        cards = new LinkedList<>();
    }
    public Hand(int size) {
        cards = new LinkedList<>();
        for(int i = 0; i < size; i++) {
            cards.addBack(deck.drawRandom());
        }
    }
    public Hand(Card[] add) {
        cards = new LinkedList<>();
        for(int i = 0; i < add.length; i++) {
            if(deck.inDeck(add[i])) cards.addBack(deck.drawCard(add[i]));
        }
    }
    @Override
    public String toString() {
        return cards.toString();
    }
    public LinkedList getCards() {
        return cards;
    }
    public int size() {
        return cards.size();
    }
    public Card getCard(int index) {
        return cards.get(index);
    }
    public Card draw() {
        Card card = deck.drawRandom();
        cards.addBack(card);
        return card;
    }
    public boolean draw(Card card) { 
        return cards.addBack(deck.drawCard(card));
    }
    public boolean returnCard(Card card) {
        deck.returnCard(card);
        return cards.remove(card);
    }
    public void returnCard(int index) {
        cards.remove(index);
    }
    public void returnCard(String type) {
        int index = firstIndex(type);
        if(index != -1) returnCard(index);
    }
    public boolean containsType(String type) {
        for(int i = 0; i < cards.size(); i++) {
            if(cards.get(i).type().equals(type)) return true;
        }
        return false;
    }
    public int firstIndex(String type) {
        for(int i = 0; i < cards.size(); i++) {
            if(cards.get(i).type().equals(type)) return i;
        }
        return -1;
    }
    
}
