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

    public void createLutemon(Lutemon lutemon) {
        this.addLutemon(lutemon);
    }

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
