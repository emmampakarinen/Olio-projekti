package com.example.olio_projekti;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TrainingArea extends Storage{
    protected ArrayList<Lutemon> TrainingLutemons = new ArrayList<>();
    private static TrainingArea ta = null;

    private void train(Lutemon lutemon) {
        lutemon.attack += 2;
        lutemon.experience += 2;
    }

    private TrainingArea() {}

    public static TrainingArea getInstance() {
        if (ta == null) {
            ta = new TrainingArea();
        }
        return ta;
    }


    public void leaveTrainingArea(Lutemon lutemon) {
        train(lutemon);
        TrainingLutemons.remove(lutemon);
    }

    public void addLutemon(Lutemon lutemon) {
        TrainingLutemons.add(lutemon);
    }

    public ArrayList<Lutemon> getLutemons() {
        return TrainingLutemons;
    }

}
