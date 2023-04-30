package com.example.olio_projekti;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BattleFieldActivity extends AppCompatActivity {
    private ImageView f1, f2, f1_fightInfo, f2_fightInfo;
    private Button returnHome;
    private TextView fightTextView, roundTextView, health1, health2;
    private ArrayList<JSONObject> battle;
    private JSONArray fightTextArray = new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battlefield);

        f1 = findViewById(R.id.fighter1);
        f1.setImageResource(BattleField.getInstance().getFighter1().getPhoto());
        f2 = findViewById(R.id.fighter2);
        f2.setImageResource(BattleField.getInstance().getFighter2().getPhoto());

        f1_fightInfo = findViewById(R.id.fightInfo1);
        f1_fightInfo.setVisibility(View.GONE);
        f2_fightInfo = findViewById(R.id.fightInfo2);
        f2_fightInfo.setVisibility(View.GONE);

        returnHome = findViewById(R.id.btnReturn);
        returnHome.setVisibility(View.GONE);

        fightTextView = findViewById(R.id.textViewFight);
        roundTextView = findViewById(R.id.textViewRound);
        health1 = findViewById(R.id.textViewHealth1);
        health2 = findViewById(R.id.textViewHealth2);
        health1.setVisibility(View.GONE);
        health2.setVisibility(View.GONE);

        fightTextView.setSingleLine(false); // reference: https://stackoverflow.com/questions/16276241/textview-displaying-text-in-one-line-when-adding-rows-programatically-in-table-l
        try {
            battle = BattleField.getInstance().fight();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        setFightTextView();

    }

    public void switchToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void setFightTextView() {
        Handler handler = new Handler(); // reference: https://stackoverflow.com/questions/40103742/update-textview-every-second-in-android

        Runnable fightRunnable = new Runnable() { // runnable solutions for showing the fight text once in 3 seconds on the screen
            int i = 0; // index of the current "round" which will be displayed on the screen
            @Override
            public void run() {
                if (i < battle.size()) { // battle.size() = the number of rounds in the fight
                    roundTextView.setText("ROUND " + (i+1)); // printing the current round
                    try {
                        health1.setText("Health: " + battle.get(i).getString("health1"));
                        health2.setText("Health: " + battle.get(i).getString("health2"));
                        health1.setVisibility(View.VISIBLE);
                        health2.setVisibility(View.VISIBLE);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        if (battle.get(i).getInt("attacker") == 1) { // setting the battle info images for the attacker and defender of current round
                            f1_fightInfo.setImageResource(R.drawable.sword);
                            f1_fightInfo.setVisibility(View.VISIBLE);
                            f2_fightInfo.setImageResource(R.drawable.shield);
                            f2_fightInfo.setVisibility(View.VISIBLE);
                        } else {
                            f1_fightInfo.setImageResource(R.drawable.shield);
                            f2_fightInfo.setImageResource(R.drawable.sword);
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        fightTextArray = battle.get(i).getJSONArray("fightTextArray"); // contains the text of the round
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    // Showing the current round text
                    Runnable nestedRunnable = new Runnable() {
                        int counter = 0;
                        @Override
                        public void run() {
                            if (counter < fightTextArray.length()) {
                                try {
                                    fightTextView.setText(fightTextArray.getString(counter)); // printing the fight text one by one until next round
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                                counter++;
                                //doneSignal.countDown();
                            }
                            if (counter < fightTextArray.length()) {
                                handler.postDelayed(this, 2750);
                            }
                        }
                    };
                    handler.post(nestedRunnable);

                    handler.postDelayed(this, 8250); /* 2750ms*3 = 8250, the delay doesn't finish until the nested
                    runnable has finished showing battle text of the current round (every round text has 3 rows of text) */
                    i++;
                } else {
                    try {
                        if (battle.get(battle.size()-1).getInt("winner") != 0) {
                            if (battle.get(battle.size()-1).getInt("winner") == 1) {
                                f1_fightInfo.setImageResource(R.drawable.crown);
                                f2_fightInfo.setImageResource(R.drawable.tomb_stone);
                            } else {
                                f1_fightInfo.setImageResource(R.drawable.tomb_stone);
                                f2_fightInfo.setImageResource(R.drawable.crown);
                            }
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    health1.setVisibility(View.GONE);
                    health2.setVisibility(View.GONE);
                    returnHome.setVisibility(View.VISIBLE);
                    roundTextView.setText("Battle ended!");
                }
            }
        };

        Runnable countdownRunnable = new Runnable() {
            Integer c = 3; // countdown
            @Override
            public void run() {
                if (c > 0) {
                    fightTextView.setText("The fight starts in " + c + "...");
                    handler.postDelayed(this, 1000);
                    c--;
                } else {
                    handler.post(fightRunnable);
                }
            }
        };
        handler.post(countdownRunnable);
    }

}
