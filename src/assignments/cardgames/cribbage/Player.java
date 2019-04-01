package assignments.cardgames.cribbage;

import cardtoolkit.*;
import globalmethods.*;
import collections.LinkedList;

/**
 * Player.java - 
 * 
 * @author Ethan Pottinger
 * @since 17-Dec-2018
 */
public class Player {
    
    private Hand hand;
    private int scoreTotal;

    public Player() {
        hand = new Hand();
        scoreTotal = 0;
    }
    public Player(int cardAmount) {
        hand = new Hand();
        for(int i = 0; i < cardAmount; i++) {
            draw();
        }
        scoreTotal = 0;
    }
    public Hand getHand() {
        return hand;
    }
    public Card draw() {
        return hand.draw();
    }
    public boolean draw(Card card) {
        return hand.draw(card);
    }
    public void returnCard(int index) {
        hand.returnCard(index);
    }
    public Card giveCard(Hand hand, Card card) {
        this.hand.returnCard(card);
        hand.draw(card);
        return card;
    }
    public void returnCards() {
        for(int i = 0; i < hand.size(); i++) {
            hand.returnCard(0);
        }
    }
    public void returnCard(Card card) {
        hand.returnCard(card);
    }
    public int getScore() {
        return countPairs()*2 + runScore() + countFifteens()*2 + flush();
    }
    public int getScore(Card cut) {
        hand.draw(cut);
        int score = countPairs()*2 + runScore() + countFifteens()*2;
        if(flush() >= 5) score += flush();
        for (int i = 0; i < hand.size(); i++) {
            if(hand.getCard(i).suit().equals(cut.suit()) && hand.getCard(i).type().equals(Card.TYPES[10])) score++;
        }
        hand.returnCard(cut);
        return score;
    }
    public int countPairs() {
        int pairs = 0;
        for(int i = 0; i < hand.size() - 1; i++) {
            for(int j = i + 1; j < hand.size(); j++) {
                if(hand.getCard(i).type().equals(hand.getCard(j).type())) pairs++;
            }
        }
        return pairs;
    }
    public int runScore() {
        int[] sort = getTypes();
        GlobalMethods.bubbleSort(sort);
        int runScore = 0;
        int run = 1;
        int multiplier = 1;
        int multipliersMultiplier = 1;
        for(int i = 0; i < sort.length; i++) {
            if(i == sort.length - 1) {
                if(run >= 3) runScore += multiplier * run * multipliersMultiplier;
            }
            
            else if(sort[i] == sort[i + 1]) multipliersMultiplier++;
            else if(sort[i] + 1 == sort[i + 1]) {
                run++;
                multiplier *= multipliersMultiplier;
                multipliersMultiplier = 1;
            }
            else if(sort[i] + 1 == sort[i + 1]) {
                run++;
                multiplier *= multipliersMultiplier;
                multipliersMultiplier = 1;
            }
            else {
                if(run >= 3) {
                    runScore += multiplier * run * multipliersMultiplier;
                    run = 1;
                    multiplier = 1;
                    multipliersMultiplier = 1;
                }
                else {
                    run = 1;
                    multiplier = 1;
                    multipliersMultiplier = 1;
                }
            }
            
        }
        return runScore;
    }
    public int countFifteens() {
        return countFifteenPairs() + countFifteenTriples() + countFifteenQuad() + countFifteenQuint();
    }
    private int countFifteenPairs() {
        int fifteens = 0;
        for(int i = 0; i < hand.size(); i++) {
            for(int j = i + 1; j < hand.size(); j++) {
                int card1 = hand.getCard(i).value();
                int card2 = hand.getCard(j).value();
                if(card1 > 10) card1 = 10;
                if(card2 > 10) card2 = 10;
                if(card1 + card2 == 15){
                    fifteens++;
                }
            }
        }
        return fifteens;
    }
    private int countFifteenTriples() {
        int fifteens = 0;
        for(int i = 0; i < hand.size(); i++) {
            for(int j = i + 1; j < hand.size(); j++) {
                for(int k = j + 1; k < hand.size(); k++) {
                    int card1 = hand.getCard(i).value();
                    int card2 = hand.getCard(j).value();
                    int card3 = hand.getCard(k).value();
                    if(card1 > 10) card1 = 10;
                    if(card2 > 10) card2 = 10;
                    if(card3 > 10) card3 = 10;
                    if(card1 + card2 + card3 == 15){
                        fifteens++;
                    }
                }    
            }
        }
        return fifteens;
    }
    private int countFifteenQuad() {
        int fifteens = 0;
        for(int i = 0; i < hand.size(); i++) {
            for(int j = i + 1; j < hand.size(); j++) {
                for(int k = j + 1; k < hand.size(); k++) {
                    for(int l = k + 1; l < hand.size(); l++) {
                        int card1 = hand.getCard(i).value();
                        int card2 = hand.getCard(j).value();
                        int card3 = hand.getCard(k).value();
                        int card4 = hand.getCard(l).value();
                        if(card1 > 10) card1 = 10;
                        if(card2 > 10) card2 = 10;
                        if(card3 > 10) card3 = 10;
                        if(card4 > 10) card4 = 10;
                        if(card1 + card2 + card3 + card4 == 15){
                            fifteens++;                        }
                    } 
                }  
            }
        }
        return fifteens;
    }
    private int countFifteenQuint() {
        if(hand.size() == 4) return 0;
        int fifteens = 0;
        for(int i = 0; i < hand.size(); i++) {
            for(int j = i + 1; j < hand.size(); j++) {
                for(int k = j + 1; k < hand.size(); k++) {
                    for(int l = k + 1; l < hand.size(); l++) {
                        for(int m = l + 1; m < hand.size(); m++) {
                            int card1 = hand.getCard(i).value();
                            int card2 = hand.getCard(j).value();
                            int card3 = hand.getCard(k).value();
                            int card4 = hand.getCard(l).value();
                            int card5 = hand.getCard(m).value();
                            if(card1 > 10) card1 = 10;
                            if(card2 > 10) card2 = 10;
                            if(card3 > 10) card3 = 10;
                            if(card4 > 10) card4 = 10;
                            if(card5 > 10) card5 = 10;
                            if(card1 + card2 + card3 + card4 + card5 == 15) {
                                fifteens++;                            }
                        } 
                    } 
                }  
            }
        }
        return fifteens;
    }
    public int flush() {
        int num = 0;
        for(int i = 0; i < Card.SUITS.length; i++) {
            if(getSuitsNum(Card.SUITS[i]) >= 4) num += getSuitsNum(Card.SUITS[i]);
        }
        return num;
    }
    public int[] getTypes() {
        int[] array = new int[hand.size()];
        for(int i = 0; i < hand.size(); i++) {
            array[i] = hand.getCard(i).value();
        }
        return array;
    }
    private int getSuitsNum(String suit) {
        int num = 0;
        for (int i = 0; i < hand.size(); i++) {
            if(suit.equals(hand.getCard(i).suit())) num++;
        }
        return num;
    } 
    public int addNumbers(LinkedList<Integer> list) {
        int num = 0;
        for (int i = 0; i < list.size(); i++) {
            num += list.get(i);
        }
        return num;
    }
    public void addScore(int score) {
        scoreTotal += score;
    }
    
}
