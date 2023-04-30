package com.example.olio_projekti;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BattleField extends Storage {
    private static BattleField bf = null;
    private static Lutemon fighter1, fighter2;
    private static Integer rounds = 0;
    protected ArrayList<Lutemon> BattleLutemons = new ArrayList<>();


    private BattleField() {}

    public static BattleField getInstance() {
        if (bf == null) {
            bf = new BattleField();
        }
        return bf;
    }

    public void addLutemon(Lutemon lutemon) {
        BattleLutemons.add(lutemon);
    }

    public void leaveBattleField(Lutemon lutemon) {
        BattleLutemons.remove(lutemon);
    }

    public ArrayList<Lutemon> getLutemons() {
        return BattleLutemons;
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

    public String getFighterStatistics() {
        String stats = "Lutemon " + fighter1.getName() + " (" + fighter1.getColor() + "), attack: " + fighter1.getAttack().toString() + ", defense: " + fighter1.getDefense().toString() + "\n" + "Lutemon " + fighter2.getName() + " (" + fighter2.getColor() + "), attack: " + fighter2.getAttack().toString() + ", defense: " + fighter2.getDefense().toString();
        return stats;
    }

    public ArrayList<JSONObject> fight() throws JSONException { // reference: https://www.tutorialspoint.com/json/json_java_example.htm
        ArrayList<JSONObject> fightInfo = new ArrayList<>(); // saving fighting info per round to JSON format
        // and saving that round info to an arrayList which helps with displaying the fight on screen at activity
        rounds = 0;

        while (fighter1.getHealth() > 0 || fighter2.getHealth() > 0) {
            JSONObject fightRound = new JSONObject(); // this object has all information of one round
            JSONArray fightText = new JSONArray(); // this array has all text info of the fight to be shown on the screen

            fightText.put(getFighterStatistics() + "\n");
            fightRound.put("health1", fighter1.getHealth());
            fightRound.put("health2", fighter2.getHealth());
            if (rounds%2 == 0) { // Determining which fighter goes.
                fightRound.put("attacker", 1);
                fightText.put("Lutemon " + fighter1.getName() + " ("+ fighter1.getColor() + ") attacks." + "\n");
                fighter2.defend(fighter1);
                if (fighter2.getHealth() <= 0) {
                    fightRound.put("winner", 1);
                    fightText.put( "Lutemon " + fighter2.getName() + " ("+ fighter2.getColor() + ") lost, " + fighter1.getName() + " ("+ fighter1.getColor() + ") won!" + "\n");

                    /* Loser's health set to zero as it cannot be negative. Winner gets 2 experience points and
                     2 attack points. */
                    fighter2.health = 0;
                    fighter1.experience = fighter1.getExperience() + 2;
                    fighter1.attack = fighter1.getAttack() + 2;

                    fightRound.put("fightTextArray", fightText);
                    fightInfo.add(fightRound);

                    // setting battle statistics
                    fighter2.setLosses(1);
                    fighter1.setWins(1);
                    break;
                } else {
                    fightText.put("Lutemon " + fighter2.getName() + " ("+ fighter2.getColor() + ") managed to dodge the attack!" + "\n");
                    fightRound.put("winner", 0); // no winner if other lutemon defended the attack
                }
            } else {
                fightRound.put("attacker", 2);
                fightText.put( "Lutemon " + fighter2.getName() + " ("+ fighter2.getColor() + ") attacks." + "\n");
                fighter1.defend(fighter2);
                if (fighter1.getHealth() <= 0) {
                    fightRound.put("winner", 2);
                    fightText.put("Lutemon " + fighter1.getName() + " ("+ fighter1.getColor() + ") lost, " + fighter2.getName() + " ("+ fighter2.getColor() + ") won!" + "\n");

                    /* Loser's health set to zero as it cannot be negative. Winner gets 2 experience points and
                     2 attack points. */
                    fighter1.health = 0; // setting health to 0 before sending home since it cannot be negative in "real life"
                    fighter2.experience = fighter2.getExperience() + 2;
                    fighter2.attack = fighter2.getAttack() + 2;

                    fightRound.put("fightTextArray", fightText);
                    fightInfo.add(fightRound);

                    // setting battle statistics
                    fighter1.setLosses(1);
                    fighter2.setWins(1);
                    break;
                } else {
                    fightText.put("Lutemon " + fighter1.getName() + " ("+ fighter1.getColor() + ") managed to dodge the attack!" + "\n");
                    fightRound.put("winner", 0); // no winner if other lutemon defended the attack
                }
            }

            fightRound.put("fightTextArray", fightText);
            rounds++;
            fightRound.put("round", rounds);
            fightInfo.add(fightRound);
        }

        // sending both Lutemons back to home after the fight
        Storage.getInstance().moveLutemon(Location.BATTLEFIELD, Location.HOME, fighter1);
        Storage.getInstance().moveLutemon(Location.BATTLEFIELD, Location.HOME, fighter2);

        return fightInfo;
    }
}
