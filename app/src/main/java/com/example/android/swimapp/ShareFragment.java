package com.example.android.swimapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


public class ShareFragment extends Fragment {
    private static final String TAG = "ShareFragment";

    public ShareFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_share, container, false);


        SetFragment setFragment = new SetFragment();

        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.firstSetContainer, setFragment).commit();



        return view;
    }
}