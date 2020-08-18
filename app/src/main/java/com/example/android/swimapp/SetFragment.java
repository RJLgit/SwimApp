package com.example.android.swimapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class SetFragment extends Fragment {

    private ShareFragment.NewSetListener newSetListener;
    private int numberOfPanelsShown = 1;
    private static final String TAG = "SetFragment";
    Boolean isVisible = false;
    String theSet = "";

    public Boolean getVisible() {
        return isVisible;
    }

    public void setVisible(Boolean visible) {
        isVisible = visible;
    }

    public String getTheSet() {
        return theSet;
    }

    public void setTheSet(String theSet) {
        this.theSet = theSet;
    }

    public SetFragment() {
        // Required empty public constructor
    }

    public ShareFragment.NewSetListener getNewSetListener() {
        return newSetListener;
    }

    public void setNewSetListener(ShareFragment.NewSetListener newSetListener) {
        this.newSetListener = newSetListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set, container, false);
        // Inflate the layout for this fragment
        final Button addSetButton = view.findViewById(R.id.addNewSetButton);
        final TextView enterSetLabel = view.findViewById(R.id.enterSetLabel);
        final EditText enterSetEditText = view.findViewById(R.id.addSetEditText);

        addSetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: " + newSetListener);
                addSetButton.setVisibility(View.INVISIBLE);
                enterSetLabel.setVisibility(View.VISIBLE);
                enterSetEditText.setVisibility(View.VISIBLE);
                newSetListener.onNewSetClicked(numberOfPanelsShown);
                numberOfPanelsShown++;
                isVisible = true;
            }
        });

        enterSetEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                theSet = editable.toString();
            }
        });

        return view;
    }
}