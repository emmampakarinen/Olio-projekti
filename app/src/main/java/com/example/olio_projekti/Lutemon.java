package com.example.olio_projekti;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Lutemon implements Serializable {
    protected String name, color;
    protected Integer attack, defense, experience, health, maxHealth, id, Lutcoin, photo;
    private static Integer idCounter = 0;
    protected HashMap<String, Integer> gear = new HashMap<>();

    public Lutemon(String name, String color, Integer attack, Integer defense, Integer maxHealth, Integer photo) {
        this.name = name;
        this.color = color;
        this.attack = attack;
        this.defense = defense;
        experience = 0;
        this.health = maxHealth;
        this.maxHealth = maxHealth;
        Lutcoin = 0;
        id = idCounter;
        gear = null;
        this.photo = photo;
        idCounter++;
    }


    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public Integer getAttack() {
        return attack;
    }

    public Integer getDefense() {
        return defense;
    }

    public Integer getExperience() {
        return experience;
    }

    public Integer getHealth() {
        return health;
    }

    public Integer getMaxHealth() {
        return maxHealth;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPhoto() {
        return photo;
    }

    public HashMap<String, Integer> getGear() {
        return gear;
    }

    public void defense(Lutemon lutemon) {
        this.health =- (lutemon.getAttack() - this.defense);
    }

    public void setHealthToMax(Integer health) {
        this.health = health;
    }
}
