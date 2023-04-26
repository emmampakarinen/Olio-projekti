package com.example.olio_projekti;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Storage {
    //protected String name;
    private static Storage storage = null;
    private static int spaId = 0, trainId = 0, homeId = 0;
    protected HashMap<Integer, Lutemon> AllLutemons = new HashMap<>();
    protected ArrayList<Lutemon> HomeLutemons = new ArrayList<>();
    protected ArrayList<Lutemon> TrainingLutemons = new ArrayList<>();
    protected ArrayList<Lutemon> SpaLutemons = new ArrayList<>();
    protected ArrayList<Lutemon> FightLutemons = new ArrayList<>();
    

    public void addLutemon(Lutemon lutemon) {
        AllLutemons.put(lutemon.getId(), lutemon);
        HomeLutemons.add(lutemon);
        homeId = lutemon.getId();
    }

    public Lutemon getLutemon(int id) {
        return AllLutemons.get(id);
    }

    public void listLutemons() {
        // kesken
    }

    public HashMap<Integer, Lutemon> getLutemons() {
        return AllLutemons;
    }

    public void moveToTraining(Lutemon lutemon) {
        if (lutemon != null) {
            TrainingLutemons.add(lutemon);
            if (HomeLutemons.contains(lutemon)) {
                HomeLutemons.remove(lutemon.getId());
            } else if (SpaLutemons.contains(lutemon)) {
                Spa.getInstance().spaTreatment(lutemon);
                SpaLutemons.remove(lutemon.getId());
            }
        }

    }

    public void moveToSpa(Lutemon lutemon) {
        if (lutemon != null) {
            SpaLutemons.add(lutemon);
            if (HomeLutemons.contains(lutemon)) {
                HomeLutemons.remove(lutemon.getId());
            } else if (TrainingLutemons.contains(lutemon)) {
                TrainingLutemons.remove(lutemon.getId());
            }
        }

    }

    public void moveToHome(Lutemon lutemon) {
        if (lutemon != null ) {
            HomeLutemons.add(lutemon);
            if (SpaLutemons.contains(lutemon)) {
                Spa.getInstance().spaTreatment(lutemon);
                SpaLutemons.remove(lutemon.getId());
            } else if (TrainingLutemons.contains(lutemon)) {
                TrainingArea.getInstance().train(lutemon);
                TrainingLutemons.remove(lutemon.getId());
            }
        }
    }

    public void moveToBattlefield(Lutemon lutemon) {
        if (lutemon != null ) {
            FightLutemons.add(lutemon);
            HomeLutemons.remove(lutemon.getId());
        }
    }

    public ArrayList<Lutemon> getHomeLutemons() {
        return HomeLutemons;
    }

    public ArrayList<Lutemon> getTrainingLutemons() {
        return TrainingLutemons;
    }

    public ArrayList<Lutemon> getSpaLutemons() {
        return SpaLutemons;
    }

    public ArrayList<Lutemon> getFightLutemons() {
        return FightLutemons;
    }
}
