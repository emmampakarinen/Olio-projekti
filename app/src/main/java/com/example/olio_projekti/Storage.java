package com.example.olio_projekti;

import android.content.Context;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Storage {
    private static Storage s = null;

    protected Storage() {}

    public static Storage getInstance() { // singleton
        if (s == null) {
            s = new Storage();
        }
        return s;
    }

    /* Moving, removing and getting lutemons in specific location is done through Storage instance,
    * which is a singleton class. Location classes (home, spa etc.) are also singletons but they
    * are only called through Storage. Enum 'Location' is used to know which location we are using. */

    public void addLutemonTo(Location location, Lutemon lutemon) {
        if (location == Location.HOME) {
            Home.getInstance().addLutemon(lutemon);
        } else if (location == Location.SPA) {
            Spa.getInstance().addLutemon(lutemon);
        } else if (location == Location.TRAINING) {
            TrainingArea.getInstance().addLutemon(lutemon);
        } else if (location == Location.BATTLEFIELD) {
            BattleField.getInstance().addLutemon(lutemon);
        }
        return;
    }

    public ArrayList<Lutemon> getLutemonsAt(Location location) {
        ArrayList<Lutemon> lutemons = new ArrayList<>();
        if (location == Location.HOME) {
            lutemons = Home.getInstance().getLutemons();
        } else if (location == Location.SPA) {
            lutemons = Spa.getInstance().getLutemons();
        } else if (location == Location.TRAINING) {
            lutemons = TrainingArea.getInstance().getLutemons();
        } else if (location == Location.BATTLEFIELD) {
            lutemons = BattleField.getInstance().getLutemons();
        }
        return lutemons;
    }

    public ArrayList<Lutemon> getAllLutemons() {
        ArrayList<Lutemon> lutemons = new ArrayList<>();
        lutemons.addAll(Home.getInstance().getLutemons());
        lutemons.addAll(Spa.getInstance().getLutemons());
        lutemons.addAll(TrainingArea.getInstance().getLutemons());
        lutemons.addAll(BattleField.getInstance().getLutemons());
        return lutemons;
    }

    public void moveLutemon(Location from, Location to, Lutemon lutemon) {
        removeLutemon(from, lutemon);
        addLutemonTo(to, lutemon);
        return;
    }

    public void removeLutemon(Location location, Lutemon lutemon) {
        if (location == Location.HOME) {
           Home.getInstance().leaveHome(lutemon);
        } else if (location == Location.SPA) {
            Spa.getInstance().leaveSpa(lutemon);
        } else if (location == Location.TRAINING) {
            TrainingArea.getInstance().leaveTrainingArea(lutemon);
        } else if (location == Location.BATTLEFIELD) {
            BattleField.getInstance().leaveBattleField(lutemon);
        }
        return;
    }


    public void saveLutemons(Context context) {
        try {
            ObjectOutputStream lutemonWriter = new ObjectOutputStream(context.openFileOutput("lutemons.data", Context.MODE_PRIVATE));
            lutemonWriter.writeObject(getAllLutemons());
            lutemonWriter.close();
            Toast.makeText(context, "Saving Lutemons succeeded", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(context, "Saving Lutemons failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void loadLutemons(Context context) {
        try {
            ArrayList<Lutemon> lutemons = new ArrayList<>();
            ObjectInputStream lutemonReader = new ObjectInputStream(context.openFileInput("lutemons.data"));
            lutemons = (ArrayList<Lutemon>) lutemonReader.readObject();
            for (Lutemon l: lutemons) {
                addLutemonTo(Location.HOME, l);
                System.out.println("moi load");
            }
            lutemonReader.close();
            Toast.makeText(context, "Loading Lutemons succeeded", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(context, "Lutemon-data empty", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(context, "Loading Lutemons failed", Toast.LENGTH_SHORT).show();
        } catch (ClassNotFoundException e) {
            Toast.makeText(context, "Loading Lutemons failed", Toast.LENGTH_SHORT).show();
        }
    }

}
