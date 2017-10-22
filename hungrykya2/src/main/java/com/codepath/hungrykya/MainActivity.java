package com.codepath.hungrykya;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements MapModeFragment.OnMapModeListener {

    private FragmentManager mFragmentManager;
    private MapModeFragment mMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mFragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            mMapFragment = new MapModeFragment();
            mFragmentManager.beginTransaction().add(R.id.tab_frame_layout, mMapFragment, MapModeFragment.TAG).commit();
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = mMapFragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = mMapFragment;
                    break;
                case R.id.navigation_dashboard:

                    break;
                case R.id.navigation_notifications:

                    break;
            }
            mFragmentManager.beginTransaction().replace(R.id.tab_frame_layout, fragment).commit();
            return true;
        }
    };

    @Override
    public void onSelected(Uri uri) {

    }
}
