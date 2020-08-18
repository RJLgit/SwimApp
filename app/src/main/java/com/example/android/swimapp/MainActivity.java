package com.example.android.swimapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ShareFragment.NewSetListener, ShareFragment.FirebaseInterface {
    private static final String TAG = "MainActivity";
    private static final int RC_SIGN_IN = 11;
    PagerAdapter pagerAdapter;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseAuth.AuthStateListener mListener;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();

        signInToFirebase();

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        TabItem findSessions = findViewById(R.id.findSessionTab);
        TabItem shareSessions = findViewById(R.id.shareSessionTab);
        ViewPager2 viewPager2 = findViewById(R.id.myViewPager);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), getLifecycle(), tabLayout.getTabCount(), this);
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

    private void signInToFirebase() {
        mListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = mAuth.getCurrentUser();
                if (user != null) {
                    updateActionBar();
                } else {
                    List<AuthUI.IdpConfig> providers = Arrays.asList(
                            new AuthUI.IdpConfig.EmailBuilder().build());

// Create and launch sign-in intent
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setAvailableProviders(providers)
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };


    }

    private void updateActionBar() {
        toolbar.setSubtitle("Signed in as: " + user.getDisplayName());
    }

    @Override
    public void onNewSetClicked(int num) {
        Log.d(TAG, "onNewSetClicked: ");
        pagerAdapter.setEntered(num);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                user = FirebaseAuth.getInstance().getCurrentUser();
                Toast.makeText(MainActivity.this, "Welcome, you are logged in " + user.getDisplayName(), Toast.LENGTH_LONG).show();
                toolbar.setSubtitle(user.getDisplayName());

                // ...
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
                Toast.makeText(MainActivity.this, "Sign in cancelled", Toast.LENGTH_LONG).show();
                finish();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out:
                signOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    private void signOut() {
        mAuth.signOut();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mListener != null) {
            mAuth.removeAuthStateListener(mListener);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAuth.addAuthStateListener(mListener);
    }

    @Override
    public void addSession(String set1, String set2, String set3, String set4) {
        Sessions mySession = new Sessions(set1, set2, set3, set4, user.getDisplayName());

    }
}