package cardtoolkit;

import globalmethods.GlobalMethods;
/**
 * Deck.java - 
 * 
 * @author Ethan Pottinger
 * @since 30-Nov-2018
 */
public class Deck {
    
    public static final int MAX = 52;

    private static int cardCount;
    
    private Card[] cards;
            
    public Deck() {
        cardCount = MAX;
        cards = new Card[MAX];
        for(int i = 0; i < MAX; i++) {
            cards[i] = new Card((i % 13) + 1, Card.SUITS[(int)(i / 13)]); 
        }
    }
    @Override
    public String toString() {
        String deck = "[";
        for(int i = 0; i < MAX - 1; i++) {
            deck += cards[i] + ", ";
        }
        return deck + cards[MAX - 1] + "]";
    }
    public Card draw(int index) {
        if(cards[index] == null) return null;
        Card card = cards[index].clone();
        cards[index] = null;
        cardCount--;
        return card;
    }
    public Card drawRandom() {
        if(cardCount == 0) return null;
        Card card = new Card();
        int index = 0;
        do {
            index = GlobalMethods.randInt(0, MAX - 1);
            card = cards[index];
        }
        while(card == null);
        cards[index] = null;
        cardCount--;
        return card;
    }
    public Card drawCard(Card card) {
        if(cardCount == 0) return null;
        for(int i = 0; i < MAX; i++) {
            if(card.equals(cards[i])) {
                cards[i] = null;
                cardCount--;
                return card;
            }
        }
        return null;
    }
    public boolean inDeck(Card card) {
        for (int i = 0; i < MAX; i++) {
            if(card.equals(cards[i])) return true;
        }
        return false;
    }
    public boolean returnCard(Card card) {
        if(card != null && !inDeck(card)) {
            int index = card.value() + (card.suitIndex() * 13) - 1;
            cards[index] = card.clone();
            cardCount++;
            return true;
        }
        return false;
    }
    public int getCount() {
        return cardCount;
    }
    
}
