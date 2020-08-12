package com.example.android.swimapp;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PagerAdapter extends FragmentStateAdapter {

    private int numOfTabs;
    private Context context;
    private static final String TAG = "PagerAdapter";
    private String[] tabTitles = new String[]{"Find Session", "Share Session"};
    private ShareFragment shareFragment;

    public PagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, int numOfTabs, Context context) {
        super(fragmentManager, lifecycle);
        this.numOfTabs = numOfTabs;
        this.context = context;
    }



    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                Log.d(TAG, "createFragment: 0");
                return new FindFragment();
            case 1:
                Log.d(TAG, "createFragment: 1");
                shareFragment = new ShareFragment();
                shareFragment.setNewSetListener((ShareFragment.NewSetListener) context);
                return shareFragment;
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return numOfTabs;
    }


    public void setEntered(int num) {
        Log.d(TAG, "setEntered: ");
        shareFragment.setEntered();
    }
}
