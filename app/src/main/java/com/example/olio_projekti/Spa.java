package com.example.olio_projekti;

import java.util.ArrayList;

public class Spa extends Storage {
    private static Spa spa = null;
    protected ArrayList<Lutemon> SpaLutemons = new ArrayList<>();
    private Spa() {}

    public static Spa getInstance() {
        if (spa == null) {
            spa = new Spa();
        }
        return spa;
    }

    // Lutemon's health goes to max after spaTreatment, method is called before leaving the spa
    private void spaTreatment(Lutemon lutemon) {
        lutemon.setHealthToMax(lutemon.getMaxHealth());
    }


    public void leaveSpa(Lutemon lutemon) {
        spaTreatment(lutemon);
        SpaLutemons.remove(lutemon);
    }

    public void addLutemon(Lutemon lutemon) {
        SpaLutemons.add(lutemon);
    }

    public ArrayList<Lutemon> getLutemons() {
        return SpaLutemons;
    }

}
