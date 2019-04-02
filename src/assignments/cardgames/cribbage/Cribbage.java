package assignments.cardgames.cribbage;

import cardtoolkit.*;
import globalmethods.*;
import collections.*;

/**
 * Cribbage.java - 
 * 
 * @author Ethan Pottinger
 * @since 15-Jan-2019
 */
public class Cribbage {

    private final int WINNING_SCORE = 120;
    private final int SKUNK_DIFFERENCE = 30;
    
    private Player player1;
    public Player player2;
    
    private Hand crib;
    
    private Card cut;
    
    private  int turn;
    
    private boolean stillPlaying;
          
    public Cribbage() {
        player1 = new Player();
        player2 = new Player();
        crib = new Hand();
        cut = new Card();
        turn = 1;
        stillPlaying = true;
    }
    
    public void play() {
        int cut = 0;
        do {
            turn = cut();
        }
        while(turn == 0);
        do {
            boolean p1Wins = false;
            boolean p2Wins = false;
            do {
                deal();
                chooseCards();
                getCutCard();
                pegging();
                int p1Points = player1.getScore(this.cut);
                int p2Points = player2.getScore(this.cut);
                System.out.println(p1Points);
                System.out.println(p2Points);
                if(turn == 1) {
                    player2.addScore(p2Points);
                    if(player2.getScoreTotal() >= 120) p2Wins = true;
                    player1.addScore(p1Points);
                    if(player1.getScoreTotal() >= 120 && !p2Wins) p1Wins = true;
                    GlobalMethods.output("You got " + p1Points + " Points and player 2 got " + p2Points + " points");
                }
                if(turn == 2) {
                    player1.addScore(p1Points);
                    if(player1.getScoreTotal() >= 120) p1Wins = true;
                    player2.addScore(p1Points);
                    if(player2.getScoreTotal() >= 120 && !p1Wins) p2Wins = true;
                    GlobalMethods.output("You got " + p1Points + " Points and player 2 got " + p2Points + " points");
                }
            }
            while(p2Wins == false && p1Wins == false);
            if(p2Wins) {
                if(WINNING_SCORE - player1.getScoreTotal() >= SKUNK_DIFFERENCE) {
                    stillPlaying = GlobalMethods.yesOrNo("Player 2 Wins! you lost with " + player1.getScoreTotal() + "Points, you were Skunked!\n\nWould you like to play again?");
                }
                else {
                    stillPlaying = GlobalMethods.yesOrNo("Player 2 Wins! you lost with " + player1.getScoreTotal() + "\n\nWould you like to play again?");
                }
            }
            else if(p1Wins) {
                if(WINNING_SCORE - player2.getScoreTotal() >= SKUNK_DIFFERENCE) {
                    stillPlaying = GlobalMethods.yesOrNo("You win! Player 2 lost with " + player2.getScoreTotal() + "Points, you Skunked player 2!\n\nWould you like to play again?");
                }
                else {
                    stillPlaying = GlobalMethods.yesOrNo("You win! Player 2 lost with " + player2.getScoreTotal() + "\n\nWould you like to play again?");
                }
            }
        }
        while(stillPlaying);
    }
    public int cut() {
        Card p1Card = Hand.deck.drawRandom();
        Card p2Card = Hand.deck.drawRandom();
        GlobalMethods.output("Player 1 drew " + p1Card.toString() + "\n\nAnd "
                + "player 2 drew " + p2Card.toString());
        if(p1Card.value() < p2Card.value()) {
            GlobalMethods.output("Player 1 wins the deal!");
            Hand.deck.returnCard(p2Card);
            Hand.deck.returnCard(p1Card);
            return 1;
            
        }
        if(p2Card.value() < p1Card.value()) {
            GlobalMethods.output("Player 2 wins the deal!");
            Hand.deck.returnCard(p2Card);
            Hand.deck.returnCard(p1Card);
            return 2;
        }
        else {
            Hand.deck.returnCard(p2Card);
            Hand.deck.returnCard(p1Card);
            return 0;
        }
    }
    public void deal() {
        if(turn == 1) {
            for(int i = 0; i < 6; i++) {
            player2.draw();
            player1.draw();
            }
        }
        if(turn == 2) {
            for(int i = 0; i < 6; i++) {
            player1.draw();
            player2.draw();
            }
        }
        System.out.println(player1.getHand().toString());
        System.out.println(player2.getHand().toString());
    }
    public void chooseCards() {
        if(turn == 1) {
            for(int i = 0; i < 2; i++) {
                String[] options = new String[player1.getHand().size()];
                for(int j = 0; j < player1.getHand().size(); j++) {
                    options[j] = player1.getHand().getCard(j).toString();
                }
                String choice = GlobalMethods.choose("Choose a card to go into your crib\n\n" + player1.getHand().toString(), "Cribbage Player 1", options);
                for(int j = 0; j < player1.getHand().size(); j++) {
                    if(player1.getHand().getCard(j).toString().equals(choice)) {
                        Card card = player1.getHand().getCard(j);
                        player1.returnCard(j);
                        crib.draw(card);
                    }
                }
            }
            Card choice1 = new Card();
            Card choice2 = new Card();
            int maxScore = 0;
            for(int i = 0; i < player2.getHand().size(); i++) {
                for(int j = i + 1; j < player2.getHand().size(); j++) {
                    Card card1 = player2.getHand().getCard(i);
                    Card card2 = player2.getHand().getCard(j);
                    player2.returnCard(card1);
                    player2.returnCard(card2);
                    if(player2.getScore() >= maxScore) {
                        choice1 = card1;
                        choice2 = card2;
                        maxScore = player2.getScore();
                    }
                    player2.draw(card1);
                    player2.draw(card2);
                }
            }
            player2.returnCard(choice1);
            player2.returnCard(choice2);
        }
        if(turn == 2) {
            for(int i = 0; i < 2; i++) {
                String[] options = new String[player1.getHand().size()];
                for(int j = 0; j < player1.getHand().size(); j++) {
                    options[j] = player1.getHand().getCard(j).toString();
                }
                String choice = GlobalMethods.choose("Choose a card to go into your opponents crib\n\n" + player1.getHand().toString(), "Cribbage Player 1", options);
                for(int j = 0; j < player1.getHand().size(); j++) {
                    if(player1.getHand().getCard(j).toString().equals(choice)) {
                        Card card = player1.getHand().getCard(j);
                        player1.returnCard(j);
                        crib.draw(card);
                    }
                }
            }
            Card choice1 = new Card();
            Card choice2 = new Card();
            int maxScore = 0;
            for(int i = 0; i < player2.getHand().size(); i++) {
                for(int j = i + 1; j < player2.getHand().size(); j++) {
                    Card card1 = player2.getHand().getCard(i);
                    Card card2 = player2.getHand().getCard(j);
                    player2.returnCard(card1);
                    player2.returnCard(card2);
                    if(player2.getScore() >= maxScore) {
                        choice1 = card1;
                        choice2 = card2;
                        maxScore = player2.getScore();
                    }
                    player2.draw(card1);
                    player2.draw(card2);
                }
            }
            player2.returnCard(choice1);
            player2.returnCard(choice2);
        }
    }
    public void getCutCard() {
        cut = Hand.deck.drawRandom();
        GlobalMethods.output("The cut card that was drawn was a " + cut);
    }
    public void pegging() {
        LinkedList<Card> cards = new LinkedList<>();
        LinkedList<Card> playerCards = player1.getHand().getCards();
        LinkedList<Card> cardsInPlay = new LinkedList();
        int num = 0;
        int optionsNum = 0;
        if(turn == 1) {
            do {
                boolean p1CanGo = false;
                boolean p2CanGo = false;
                if(playerCards.size() != 0) {
                    do {
                        p1CanGo = false;
                        for(int i = 0; i < playerCards.size(); i++) {
                            if(playerCards.get(i).valueWithFaces10() + num <= 31) {
                                optionsNum++;
                                p1CanGo = true;
                            }
                        }
                        String[] options = new String[optionsNum];
                        if(p1CanGo) {
                            for(int j = 0; j < options.length; j++) {
                                if(playerCards.get(j).valueWithFaces10() + num <= 31) options[j] = playerCards.get(j).toString();
                            }
                            String choice = GlobalMethods.choose(cards.toString() + "\n" + cardsInPlay.toString() + "\n" + num + "\n\nPlayer1: " + player1.getScoreTotal() + "\nPlayer2: " + player2.getScoreTotal() + "\n\nChoose a card to play\n" + playerCards.toString(), "Cribbage Player 1", options);
                            for(int j = 0; j < playerCards.size(); j++) {
                                if(playerCards.get(j).toString().equals(choice)) {
                                    int pointsToAdd = 0;
                                    Card card = playerCards.get(j);
                                    playerCards.remove(card);
                                    cards.add(card);
                                    
                                    num += card.valueWithFaces10();
                                    if(num == 15 || num == 31) pointsToAdd += 2;
                                    cardsInPlay.add(card);
                                    pointsToAdd += getPegPairsPoints(cardsInPlay) + getPegRun(cardsInPlay);
                                    GlobalMethods.output(num + " For " + pointsToAdd);
                                    player1.addScore(pointsToAdd);
                                    if(cards.size() == 8) {
                                        GlobalMethods.output("Last card for 1");
                                        player1.addScore(1);
                                    }
                                    if(num == 31) {
                                        cardsInPlay.clear();
                                        cardsInPlay.size();
                                        num = 0;
                                    }
                                    optionsNum = 0;
                                }
                            }
                        }
                        else {
                            GlobalMethods.output("Go, Player 2 gets 1 point");
                            player2.addScore(1);
                            cardsInPlay.clear();
                            num = 0;
                        }
                    }
                    while(!p1CanGo);
                }
                if(player2.getHand().size() != 0) {
                    do {
                        p2CanGo = false;
                        int maxScore = 0;
                        int points = 0;
                        int option = 0;
                        for(int i = 0; i < player2.getHand().size(); i++) {
                            Card card = (Card)player2.getHand().getCards().get(i);
                            if(card.valueWithFaces10() + num <= 31) {
                                p2CanGo = true;
                                int pointsToAdd = 0;
                                cardsInPlay.add(card);
                                num += card.valueWithFaces10();
                                if(num == 15 || num == 31) pointsToAdd += 2;
                                pointsToAdd += getPegPairsPoints(cardsInPlay) + getPegRun(cardsInPlay);
                                if(pointsToAdd >= maxScore) {
                                    maxScore = pointsToAdd;
                                    option = i;
                                }
                            if(num == 15 || num == 31) pointsToAdd -= 2;
                            pointsToAdd -= getPegPairsPoints(cardsInPlay) - getPegRun(cardsInPlay);
                            num -= card.valueWithFaces10();
                            cardsInPlay.remove(card);
                            }
                        }
                        if(p2CanGo) {
                            Card card = (Card)player2.getHand().getCards().get(option);
                            cardsInPlay.add(card);
                            player2.returnCard(card);
                            cards.add(card);
                            num += card.valueWithFaces10();
                            if(num == 15 || num == 31) points += 2;
                            points += getPegPairsPoints(cardsInPlay) + getPegRun(cardsInPlay);
                            GlobalMethods.output("Player 2 plays " + card + " and gets " + num + " for " + points);
                            player2.addScore(points);
                            if(cards.size() == 8) {
                                GlobalMethods.output("Player 2 gets Last card for 1");
                                player2.addScore(1);
                            }
                            if(num == 31) {
                                cardsInPlay.clear();
                                cardsInPlay.size();
                                num = 0;
                            }
                        }
                        else {
                            GlobalMethods.output("Player 2 says go, player 1 gets 1 point");
                            player1.addScore(1);
                            cardsInPlay.clear();
                            num = 0;
                        }
                    }
                    while(!p2CanGo);
                }
            }
            while(cards.size() != 8);
        }
        if(turn == 2) {
            do {
                boolean p1CanGo = false;
                boolean p2CanGo = false;
                if(player2.getHand().size() != 0) {
                    do {
                        p2CanGo = false;
                        int maxScore = 0;
                        int points = 0;
                        int option = 0;
                        for(int i = 0; i < player2.getHand().size(); i++) {
                            Card card = (Card)player2.getHand().getCards().get(i);
                            if(card.valueWithFaces10() + num <= 31) {
                                p2CanGo = true;
                                int pointsToAdd = 0;
                                cardsInPlay.add(card);
                                num += card.valueWithFaces10();
                                if(num == 15 || num == 31) pointsToAdd += 2;
                                pointsToAdd += getPegPairsPoints(cardsInPlay) + getPegRun(cardsInPlay);
                                if(pointsToAdd >= maxScore) {
                                    maxScore = pointsToAdd;
                                    option = i;
                                }
                            if(num == 15 || num == 31) pointsToAdd -= 2;
                            pointsToAdd -= getPegPairsPoints(cardsInPlay) - getPegRun(cardsInPlay);
                            num -= card.valueWithFaces10();
                            cardsInPlay.remove(card);
                            }
                        }
                        if(p2CanGo) {
                            Card card = (Card)player2.getHand().getCards().get(option);
                            cardsInPlay.add(card);
                            player2.returnCard(card);
                            cards.add(card);
                            
                            num += card.valueWithFaces10();
                            if(num == 15 || num == 31) points += 2;
                            points += getPegPairsPoints(cardsInPlay) + getPegRun(cardsInPlay);
                            GlobalMethods.output("Player 2 plays " + card + " and gets " + num + " for " + points);
                            player2.addScore(points);
                            if(cards.size() == 8) {
                                GlobalMethods.output("Player 2 gets Last card for 1");
                                player2.addScore(1);
                            }
                            if(num == 31) {
                                cardsInPlay.clear();
                                cardsInPlay.size();
                                num = 0;
                            }
                        }
                        else {
                            GlobalMethods.output("Player 2 says go, player 1 gets 1 point");
                            player1.addScore(1);
                            cardsInPlay.clear();
                            num = 0;
                        }
                    }
                    while(!p2CanGo);
                }
                if(playerCards.size() != 0) {
                    do {
                        p1CanGo = false;
                        for(int i = 0; i < playerCards.size(); i++) {
                            if(playerCards.get(i).valueWithFaces10() + num <= 31) {
                                optionsNum++;
                                p1CanGo = true;
                            }
                        }
                        String[] options = new String[optionsNum];
                        if(p1CanGo) {
                            for(int j = 0; j < options.length; j++) {
                                if(playerCards.get(j).valueWithFaces10() + num <= 31) options[j] = playerCards.get(j).toString();
                            }
                            String choice = GlobalMethods.choose(cards.toString() + "\n" + cardsInPlay.toString() + "\n" + num + "\n\nPlayer1: " + player1.getScoreTotal() + "\nPlayer2: " + player2.getScoreTotal() + "\n\nChoose a card to play\n" + playerCards.toString(), "Cribbage Player 1", options);
                            for(int j = 0; j < playerCards.size(); j++) {
                                if(playerCards.get(j).toString().equals(choice)) {
                                    int pointsToAdd = 0;
                                    Card card = playerCards.get(j);
                                    playerCards.remove(card);
                                    cards.add(card);
                                    num += card.valueWithFaces10();
                                    if(num == 15 || num == 31) pointsToAdd += 2;
                                    cardsInPlay.add(card);
                                    pointsToAdd += getPegPairsPoints(cardsInPlay) + getPegRun(cardsInPlay);
                                    GlobalMethods.output(num + " For " + pointsToAdd);
                                    player1.addScore(pointsToAdd);
                                    if(cards.size() == 8) {
                                        GlobalMethods.output("Last card for 1");
                                        player1.addScore(1);
                                    }
                                    if(num == 31) {
                                        cardsInPlay.clear();
                                        cardsInPlay.size();
                                        num = 0;
                                    }
                                    optionsNum = 0;
                                }
                            }
                        }
                        else {
                            GlobalMethods.output("Go, Player 2 gets 1 point");
                            player2.addScore(1);
                            cardsInPlay.clear();
                            num = 0;
                        }
                    }
                    while(!p1CanGo);
                }
            }
            while(cards.size() != 8);
        }
    }
    
    /**
     * Counts the points earned from runs in pegging
     * 
     * @param cards The sublist for the cards being played
     * @return The amount of points gotten
     */
    public int getPegRun(LinkedList<Card> cards) {
        int score = 0;
        for(int i = 3; i <= cards.size(); i++) {
            int[] array = new int[i];
            for(int j = 0; j < array.length; j++) {
                array[j] = cards.get(cards.size() - j - 1).value();
            }
            GlobalMethods.bubbleSort(array);
            int run = 1;
            for(int j = 0; j < array.length - 1; j++) {
                if(array[j] + 1 == array[j + 1]) run++;
                else run = 1;
            }
            if(run >= i) score = run;
        }
        return score;
    }
    public int getPegPairsPoints(LinkedList<Card> cards) {
        int sameCards = 1;
        for(int i = cards.size() - 1; i > 0; i--) {
            if(cards.get(i).value() == cards.get(i - 1).value()) {
                sameCards++;
            }
            else return 0;
        }
        if(sameCards == 2) return 2;
        if(sameCards == 3) return 6;
        if(sameCards == 4) return 12;
        return 0;
    }
 
}
