package com.example.olio_projekti;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LutemonFragmentViewHolder extends RecyclerView.ViewHolder {
    TextView name, health, attack, exp;
    ImageView photo;
    Button place1, place2, readyToFight;

    public LutemonFragmentViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.textViewNameFrag);
        photo = itemView.findViewById(R.id.imageViewLutemonFrag);
        health = itemView.findViewById(R.id.textViewHealthFrag);
        attack = itemView.findViewById(R.id.textViewAttackFrag);
        exp = itemView.findViewById(R.id.textViewExpFrag);
        place1 = itemView.findViewById(R.id.btnPlace1);
        place2 = itemView.findViewById(R.id.btnPlace2);
        readyToFight = itemView.findViewById(R.id.btnReadyToFight);
    }
}
