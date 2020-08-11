package com.example.android.swimapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        TabItem findSessions = findViewById(R.id.findSessionTab);
        TabItem shareSessions = findViewById(R.id.shareSessionTab);
        ViewPager2 viewPager2 = findViewById(R.id.myViewPager);
    }
}