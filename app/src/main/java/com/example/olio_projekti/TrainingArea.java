package com.example.olio_projekti;

import java.util.ArrayList;

public class TrainingArea extends Storage{
    private static TrainingArea ta = null;

    public static TrainingArea getInstance() {
        if (ta == null) {
            ta = new TrainingArea();
        }
        return ta;
    }
    public void train(Lutemon lutemon) {
        lutemon.attack += 2;
        lutemon.experience += 2;
    }

}
