package cardtoolkit;

/**
 * Card.java - 
 * 
 * @author Ethan Pottinger
 * @since 30-Nov-2018
 */
public class Card {
    
    public static final int SPADES = 0;
    public static final int CLUBS = 1;
    public static final int DIAMONDS = 2;
    public static final int HEARTS = 3;
    public static final String[] SUITS = {"Spades", "Clubs", "Diamonds", "Hearts"};
    public static final String[] TYPES = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
 
    private final int value;
    
    private final String suit;
    private final String type;
    
    public Card() {
        value = 1;
        suit = SUITS[SPADES];
        type = TYPES[value - 1];
    }
    public Card(int value, String suit) {
        this.value = value;
        this.suit = suit;
        type = TYPES[value - 1];
    }
    @Override
    public String toString() {
        if(suit == null || type == null) return "Null";
        return type + " of " + suit; 
    }
    @Override
    public boolean equals(Object object) {
        Card that = (Card)object;
        if(that == null) return false;
        if(this.value() != that.value()) return false;
        if(!this.suit().equals(that.suit())) return false;
        return true;
    }
    @Override
    public Card clone() {
        if(this != null) return new Card(value, suit);
        return null;
    }
    public int value() {
        return value;
    }
    public int valueWithFaces10() {
        if(type.equals(TYPES[10]) || type.equals(TYPES[11]) || type.equals(TYPES[12])) return 10;
        else return value;
    }
    public String suit() {
        return suit;
    }
    public int suitIndex() {
        for(int i = 0; i < SUITS.length; i++) {
            if(SUITS[i].equals(suit)) return i;
        }
        return -1;
    }
    public int typeIndex() {
        for(int i = 0; i < TYPES.length; i++) {
            if(TYPES[i].equals(type)) return i;
        }
        return -1;
    }
    public String type() {
        return type;
    }

}
