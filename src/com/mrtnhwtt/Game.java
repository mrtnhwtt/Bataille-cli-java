package com.mrtnhwtt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private ArrayList<Card> pack;
    private Player playerOne;
    private Player playerTwo;
//    private boolean automatic;
    private Scanner scanner;

    public Game() {
        scanner = new Scanner(System.in);
        System.out.println("Enter player1's name : ");
        playerOne = new Player(scanner.nextLine());
        System.out.println("Enter player2's name : ");
        playerTwo = new Player(scanner.nextLine());
        generateDeckOfCard();
        distributeCardsToPlayers();
    }
    private void generateDeckOfCard() {
        pack = new ArrayList<Card>();
        String name;
        int strength;
        String type;
        for (int i = 0; i <=12 ; i++) {
            name = generateCardNameFromStrength(i);
            strength = i + 2;
            for (int j = 0; j <= 3; j++) {
                String[] types = {"spade", "diamond","heart", "clubs"};
                type = types[j];
                pack.add(new Card(strength, type, name));
            }
        }
        Collections.shuffle(pack);
        printDeck();
    }

    private String generateCardNameFromStrength(int index) {
        String[] names = {"two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "jack", "queen", "king", "ace"};
        return names[index];
    }

    private void printDeck() {
        for (int i = 0; i < pack.size(); i++) {
            Card card = pack.get(i);
            System.out.println((i+1) + " : " + card.getCardInfo());
        }
    }

    private void distributeCardsToPlayers() {
        for (int i = 0; i < pack.size(); i++) {
            if( (i+1) % 2 != 0) {
                playerOne.addCard(pack.get(i));
            } else {
                playerTwo.addCard(pack.get(i));
            }
        }
        playerOne.shuffleHand();
        playerTwo.shuffleHand();
    }
    public void playGame() {
        int roundCount = 0;
        int loserScore;
        boolean playing = true;
        System.out.println("Game Start!");
        while(playing) {
            loserScore = playRound();
            roundCount++;
            if(loserScore == 0) {
                displayGameResults();
                System.out.println(roundCount + " rounds played");
                playing = false;
            }
        }
    }
    private int playRound() {
        int loserScore;
        Card playerOneCard = playerOne.playCard();
        Card playerTwoCard = playerTwo.playCard();
        ArrayList<Card> stack = new ArrayList<>();
        stack.add(playerOneCard);
        stack.add(playerTwoCard);
        System.out.println(playerOne.getName() + " played a " + playerOneCard.getCardInfo());
        System.out.println(playerTwo.getName() + " played a " + playerTwoCard.getCardInfo());

        while(playerOneCard.getStrength() == playerTwoCard.getStrength()) {
            System.out.println("BATAILLE");
            ArrayList<Card> playerOneBataille = playerOne.bataille();
            if(playerOneBataille.size() > 0) {
                playerOneCard = playerOneBataille.get(playerOneBataille.size()-1);
            }
            ArrayList<Card> playerTwoBataille = playerTwo.bataille();
            if(playerTwoBataille.size() > 0) {
                playerTwoCard = playerTwoBataille.get(playerTwoBataille.size()-1);
            }
            stack.addAll(playerOneBataille);
            stack.addAll(playerTwoBataille);
            System.out.println(playerOne.getName() + " played a " + playerOneCard.getCardInfo());
            System.out.println(playerTwo.getName() + " played a " + playerTwoCard.getCardInfo());
        }
        if(playerOneCard.getStrength() > playerTwoCard.getStrength()) {
            playerOne.addCards(stack);
            loserScore = playerTwo.getScore();
        } else {
            playerTwo.addCards(stack);
            loserScore = playerOne.getScore();
        }
        System.out.println("Round result :\n" + playerOne.getName() + " score : " + playerOne.getScore() + "\n" + playerTwo.getName() + " score : " + playerTwo.getScore() + "\n");
        return loserScore;
    }
    private void displayGameResults() {
        String winner;
        if(playerOne.getScore() > 0) {
           winner = playerOne.getName();
        } else {
            winner = playerTwo.getName();
        }
        System.out.println(winner + " won the game !");
    }
}
