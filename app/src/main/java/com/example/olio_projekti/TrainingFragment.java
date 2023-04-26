package com.example.olio_projekti;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrainingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrainingFragment extends Fragment {


    private Storage LutemonStorage;
    private RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_training, container, false);

        LutemonStorage = Storage.getInstance();

        rv = view.findViewById(R.id.rvLutemonsTrainFrag);

        rv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rv.setAdapter(new LutemonFragmentListAdapter(view.getContext(), LutemonStorage.getTrainingLutemons(), this));
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(layoutManager);

        return view;
    }

}