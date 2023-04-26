package com.example.olio_projekti;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BattleFieldActivity extends AppCompatActivity {
    private ImageView f1, f2, f1_fightInfo, f2_fightInfo;
    private Button returnHome;
    private TextView fightText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battlefield);

        f1 = findViewById(R.id.fighter1);
        f1.setImageResource(BattleField.getInstance().getFighter1().getPhoto());
        f2 = findViewById(R.id.fighter2);
        f2.setImageResource(BattleField.getInstance().getFighter2().getPhoto());
        f1_fightInfo = findViewById(R.id.fightInfo1);
        f2_fightInfo = findViewById(R.id.fightInfo2);
        returnHome = findViewById(R.id.btnReturn);
        returnHome.setVisibility(View.GONE);
        fightText = findViewById(R.id.textViewFight);
        BattleField.getInstance().fight(fightText);
        returnHome.setVisibility(View.VISIBLE);
    }

    public void switchToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
