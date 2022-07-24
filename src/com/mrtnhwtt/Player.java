package com.mrtnhwtt;

import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private String name;
    private ArrayList<Card> hand;
    private int score;

    public Player(String name) {
        this.name = name;
        hand = new ArrayList<Card>();
        this.score = 0;
    }
    public void addCard(Card newCard) {
        hand.add(hand.size(), newCard);
        score ++;
    }
    public void addCards(ArrayList<Card> newCards) {
        for (int i = 0; i < newCards.size(); i++) {
            addCard(newCards.get(i));
        }
    }
    public void shuffleHand() {
        Collections.shuffle(hand);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getScore() {
        return score;
    }

    public void printHand() {
        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.get(i);
            System.out.println(card.getCardInfo());
        }
    }
    public Card playCard() {
        if(hand.size() > 0) {
            Card playedCard = hand.get(0);
            hand.remove(0);
            score--;
            return playedCard;
        } else {
            return null;
        }
    }
    public ArrayList<Card> bataille() {
        ArrayList<Card> playedCards = new ArrayList<>();
        for (int i = 0; i <= 1; i++) {
            Card card = playCard();
            if(card != null) {
                playedCards.add(card);
            }
        }
        return playedCards;
    }
}
