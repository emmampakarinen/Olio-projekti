package com.example.olio_projekti;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CheckBoxHolder extends RecyclerView.ViewHolder {
    CheckBox cb;
    ImageButton home;

    public CheckBoxHolder(@NonNull View itemView) {
        super(itemView);
        cb = itemView.findViewById(R.id.checkBoxFightMenu);
        home = itemView.findViewById(R.id.btnBackHome);
    }
}
