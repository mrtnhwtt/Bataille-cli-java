package com.mrtnhwtt;

public class Card {
    private int strength;
    private String type;
    private String name;

    public Card(int strength, String type, String name) {
        this.strength = strength;
        this.type = type;
        this.name = name;
    }

    public int getStrength() {
        return strength;
    }

    public String getCardInfo() {
        String info = name + " of " + type + ", strength : " + strength;
        return info;
    }
}
