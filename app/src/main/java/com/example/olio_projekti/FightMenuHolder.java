package com.example.olio_projekti;

import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FightMenuHolder extends RecyclerView.ViewHolder {
    CheckBox cb;

    public FightMenuHolder(@NonNull View itemView) {
        super(itemView);
        cb = itemView.findViewById(R.id.checkBoxFightMenu);
    }
}
