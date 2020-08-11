package com.example.android.swimapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        TabItem findSessions = findViewById(R.id.findSessionTab);
        TabItem shareSessions = findViewById(R.id.shareSessionTab);
        ViewPager2 viewPager2 = findViewById(R.id.myViewPager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), getLifecycle(), tabLayout.getTabCount());
        viewPager2.setAdapter(pagerAdapter);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2,
                true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

            }
        });
        tabLayoutMediator.attach();

        tabLayout.getTabAt(0).setText("Find Sessions");
        tabLayout.getTabAt(1).setText("Share Sessions");


    }
}