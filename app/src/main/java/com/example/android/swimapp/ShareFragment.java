package com.example.android.swimapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;


public class ShareFragment extends Fragment {
    private static final String TAG = "ShareFragment";
    NewSetListener newSetListener;
    private int setsShown = 1;
    FrameLayout secondContainer;
    FrameLayout thirdContainer;
    FrameLayout fourthContainer;
    Button submitButton;


    public ShareFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_share, container, false);

        secondContainer = view.findViewById(R.id.secondSetContainer);
        thirdContainer = view.findViewById(R.id.thirdSetContainer);
        fourthContainer = view.findViewById(R.id.fourthSetContainer);
        submitButton = view.findViewById(R.id.addSessionButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        SetFragment setFragment = new SetFragment();
        setFragment.setNewSetListener(newSetListener);
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.firstSetContainer, setFragment).commit();



        return view;
    }

    public NewSetListener getNewSetListener() {
        return newSetListener;
    }

    public void setNewSetListener(NewSetListener newSetListener) {
        this.newSetListener = newSetListener;
    }

    public void setEntered() {
        Log.d(TAG, "setEntered: " + setsShown);
        switch (setsShown) {
            case 1:
                Log.d(TAG, "setEntered: case 1");
                secondContainer.setVisibility(View.VISIBLE);
                SetFragment setFragment = new SetFragment();
                setFragment.setNewSetListener(newSetListener);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.secondSetContainer, setFragment).commit();
                break;
            case 2:
                Log.d(TAG, "setEntered: case2");
                thirdContainer.setVisibility(View.VISIBLE);
                SetFragment setFragment2 = new SetFragment();
                setFragment2.setNewSetListener(newSetListener);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.thirdSetContainer, setFragment2).commit();
                break;
            case 3:
                Log.d(TAG, "setEntered: case 3");
                fourthContainer.setVisibility(View.VISIBLE);
                SetFragment setFragment3 = new SetFragment();
                setFragment3.setNewSetListener(newSetListener);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.fourthSetContainer, setFragment3).commit();
                break;
            default:
                Log.d(TAG, "setEntered: error");
                break;
        }
        setsShown++;
        Log.d(TAG, "setEntered: " + setsShown);
    }

    public interface NewSetListener {
        public void onNewSetClicked(int num);
    }
}