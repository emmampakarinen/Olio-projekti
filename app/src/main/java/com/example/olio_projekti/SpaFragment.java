package com.example.olio_projekti;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class SpaFragment extends Fragment {
    private Storage LutemonStorage;
    private RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_spa, container, false);

        LutemonStorage = Storage.getInstance();

        rv = view.findViewById(R.id.rvSpaFrag);

        rv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rv.setAdapter(new LutemonFragmentListAdapter(view.getContext(), LutemonStorage.getSpaLutemons(), this));
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(layoutManager);

        return view;
    }
}