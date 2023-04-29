package com.example.olio_projekti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;

public class ImageArrayAdapter extends ArrayAdapter<Integer> {
    private Context context;
    private Integer[] images;

    public ImageArrayAdapter(@NonNull Context context, Integer[] resource) {
        super(context, R.layout.spinner_item, resource);
        this.context = context;
        images = resource;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.spinner_item, null);
        }

        ImageView iv = view.findViewById(R.id.ivLutemonPic);
        iv.setImageResource(images[position]);


        return view;
    }


}