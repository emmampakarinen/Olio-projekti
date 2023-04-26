package com.example.olio_projekti;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddLutemonActivity extends AppCompatActivity {
    private String color, name;
    private Integer attack, defense, maxHealth, selected_photo;
    private RadioGroup rgLutemon;
    private Spinner spinner;
    private Integer pinkImages[] = {R.drawable.pinkcat1, R.drawable.pinkfrog};
    private Integer whiteImages[] = {R.drawable.whitelutemon1, R.drawable.whitelutemon2};
    private Integer greenImages[] = {R.drawable.greenlutemon1, R.drawable.greenlutemon2};
    private Integer orangeImages[] = {R.drawable.orangelutemon1, R.drawable.orangelutemon2};
    private Integer blackImages[] = {R.drawable.blacklutemon1, R.drawable.blacklutemon2};
    int pos;
    ImageArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lutemon);

        spinner = (Spinner) findViewById(R.id.spinnerLutemonPic);
        rgLutemon = (RadioGroup) findViewById(R.id.rgLutemon);

        TextView spinnertitle = findViewById(R.id.textViewSpinnerTitle);
        spinnertitle.setVisibility(View.GONE);
        spinner.setVisibility(View.GONE);

        spinner.setPrompt("Choose a picture for the Lutemon");

        rgLutemon.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                pos = rgLutemon.indexOfChild(findViewById(i));
                spinnertitle.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.VISIBLE);

                switch(pos) {
                    case 0:
                        adapter = new ImageArrayAdapter(getApplicationContext(), whiteImages);
                        spinner.setAdapter(adapter);
                        break;
                    case 1:
                        adapter = new ImageArrayAdapter(getApplicationContext(), greenImages);
                        spinner.setAdapter(adapter);
                        break;
                    case 2:
                        adapter = new ImageArrayAdapter(getApplicationContext(), pinkImages);
                        spinner.setAdapter(adapter);
                        break;
                    case 3:
                        adapter = new ImageArrayAdapter(getApplicationContext(), orangeImages);
                        spinner.setAdapter(adapter);
                        break;
                    case 4:
                        adapter = new ImageArrayAdapter(getApplicationContext(), blackImages);
                        spinner.setAdapter(adapter);
                        break;
                }
            }
        });
    }

    public void addLutemon(View view) {
        EditText etName = findViewById(R.id.editTextName);
        name = etName.getText().toString();


        switch (rgLutemon.getCheckedRadioButtonId()) {
            case R.id.rbWhite:
                color = "White";
                attack = 5;
                defense = 4;
                maxHealth = 20;

                switch(spinner.getSelectedItemPosition()) {
                    case 0:
                        selected_photo = R.drawable.whitelutemon1;
                        break;
                    case 1:
                        selected_photo = R.drawable.whitelutemon2;
                        break;
                }

                Storage.getInstance().addLutemon(new Black(name, color, attack, defense, maxHealth, selected_photo));
                break;
            case R.id.rbGreen:
                color = "Green";
                attack = 6;
                defense = 3;
                maxHealth = 19;

                switch(spinner.getSelectedItemPosition()) {
                    case 0:
                        selected_photo = R.drawable.greenlutemon1;
                        break;
                    case 1:
                        selected_photo = R.drawable.greenlutemon2;
                        break;
                }

                Storage.getInstance().addLutemon(new Green(name, color, attack, defense, maxHealth, selected_photo));
                break;
            case R.id.rbPink:
                color = "Pink";
                attack = 7;
                defense = 2;
                maxHealth = 18;

                switch(spinner.getSelectedItemPosition()) {
                    case 0:
                        selected_photo = R.drawable.pinkcat1;
                        break;
                    case 1:
                        selected_photo = R.drawable.pinkfrog;
                        break;
                }

                Storage.getInstance().addLutemon(new Pink(name, color, attack, defense, maxHealth, selected_photo));
                break;
            case R.id.rbOrange:
                color = "Orange";
                attack = 8;
                defense = 1;
                maxHealth = 17;

                switch(spinner.getSelectedItemPosition()) {
                    case 0:
                        selected_photo = R.drawable.orangelutemon1;
                        break;
                    case 1:
                        selected_photo = R.drawable.orangelutemon2;
                        break;
                }

                Storage.getInstance().addLutemon(new White(name, color, attack, defense, maxHealth, selected_photo));
                break;
            case R.id.rbBlack:
                color = "Black";
                attack = 9;
                defense = 0;
                maxHealth = 16;

                switch(spinner.getSelectedItemPosition()) {
                    case 0:
                        selected_photo = R.drawable.blacklutemon1;
                        break;
                    case 1:
                        selected_photo = R.drawable.blacklutemon2;
                        break;
                }

                Storage.getInstance().addLutemon(new Orange(name, color, attack, defense, maxHealth, selected_photo));
                break;
        }
    }
}
