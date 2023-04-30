package com.example.olio_projekti;

import java.util.ArrayList;

public class Home extends Storage {
    protected ArrayList<Lutemon> HomeLutemons = new ArrayList<>();
    private static Home home = null;

    private Home(){}

    public static Home getInstance() {
        if (home == null) {
            home = new Home();
        }
        return home;
    }

    /* createLutemon() class was redundant due to addLutemon method in this class, so it
     was removed. */
    public void addLutemon(Lutemon lutemon) {
        HomeLutemons.add(lutemon);
    }

    public void leaveHome(Lutemon lutemon) {
        HomeLutemons.remove(lutemon);
    }

    public ArrayList<Lutemon> getLutemons() {
        return HomeLutemons;
    }


}
