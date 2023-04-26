package com.example.olio_projekti;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BattleField {
    private static BattleField bf = null;
    private static Lutemon fighter1, fighter2;
    private static int rounds = 0;


    private BattleField() {}

    public static BattleField getInstance() {
        if (bf == null) {
            bf = new BattleField();
        }
        return bf;
    }



    public void setFighters(Lutemon lutemon1, Lutemon lutemon2) {
        fighter1 = lutemon1;
        fighter2 = lutemon2;
    }

    public Lutemon getFighter1() {
        return fighter1;
    }

    public Lutemon getFighter2() {
        return fighter2;
    }

    public String getStatistics() {
        String stats = "Lutemon " + fighter1.getName() + "(" + fighter1.getColor() + "), health: " + fighter1.getHealth().toString() + ", attack: " + fighter1.getAttack().toString() + ", defense: " + fighter1.getDefense().toString() + "\n" + "Lutemon " + fighter2.getName() + "(" + fighter2.getColor() + "), health: " + fighter2.getHealth().toString() + ", attack: " + fighter2.getAttack().toString() + ", defense: " + fighter2.getDefense().toString() + "\n";
        return stats;
    }

    public void fight(TextView tv) {
        while (fighter1.getHealth() > 0 || fighter2.getHealth() > 0) {
            tv.setText(getStatistics());
            if (rounds%2 == 0) {
                tv.setText("Lutemon " + fighter1.getName() + "("+ fighter1.getColor() + ")attacks.\n");
                fighter2.defense(fighter1);
                if (fighter2.getHealth() <= 0) {
                    tv.setText("Lutemon " + fighter2.getName() + "("+ fighter2.getColor() + ") died, " + fighter1.getName() + "("+ fighter1.getColor() + ") won!\n");
                    fighter2.health = 0;
                    fighter1.experience = fighter1.getExperience() + 1;
                    fighter1.attack = fighter1.getAttack() + 2;
                    break;
                } else {
                    tv.setText("Lutemon " + fighter2.getName() + "("+ fighter2.getColor() + ") defends the attack!\n");
                }
            } else {
                tv.setText("Lutemon " + fighter2.getName() + "("+ fighter2.getColor() + ") attacks.\n");
                fighter1.defense(fighter2);
                if (fighter1.getHealth() <= 0) {
                    tv.setText("Lutemon " + fighter1.getName() + "("+ fighter1.getColor() + ") died, " + fighter2.getName() + "("+ fighter2.getColor() + ") won!\n");
                    fighter1.health = 0;
                    fighter2.experience = fighter2.getExperience() + 1;
                    fighter2.attack = fighter2.getAttack() + 2;
                    break;
                } else {
                    tv.setText("Lutemon " + fighter1.getName() + "("+ fighter1.getColor() + ") defends the attack!\n");
                }
            }
            rounds++;
        }
        Storage.getInstance().moveToHome(fighter1);
        Storage.getInstance().moveToHome(fighter2);
    }
}
