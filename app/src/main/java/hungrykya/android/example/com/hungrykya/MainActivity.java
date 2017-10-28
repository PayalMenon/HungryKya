package hungrykya.android.example.com.hungrykya;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import hungrykya.android.example.com.hungrykya.fragments.ListModeFragment;
import hungrykya.android.example.com.hungrykya.fragments.MapModeFragment;
import hungrykya.android.example.com.hungrykya.fragments.PreferenceFragment;
import hungrykya.android.example.com.hungrykya.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity implements MapModeFragment.OnMapModeListener, ListModeFragment.OnListModeListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private FragmentManager mFragmentManager;
    private MapModeFragment mMapFragment;
    private ListModeFragment mListFragment;
    private PreferenceFragment mPreferenceFragment;
    private ProfileFragment mProfileFragment;
    private boolean isListMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mFragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            mMapFragment = new MapModeFragment();
            mListFragment = new ListModeFragment();
            mFragmentManager.beginTransaction().add(R.id.tab_frame_layout, mListFragment, ListModeFragment.TAG).commit();
            isListMode = true;
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_dashboard, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);

        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("HungryKya", "searched for" + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = mMapFragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = isListMode ? mListFragment : mMapFragment;
                    break;
                case R.id.navigation_preference:
                    if (mPreferenceFragment == null) {
                        mPreferenceFragment = new PreferenceFragment();
                    }
                    fragment = mPreferenceFragment;
                    break;
                case R.id.navigation_profile:
                    if (mProfileFragment == null) {
                        mProfileFragment = new ProfileFragment();
                    }
                    fragment = mProfileFragment;
                    break;
            }
            mFragmentManager.beginTransaction().replace(R.id.tab_frame_layout, fragment).commit();
            return true;
        }
    };

    public void onSwitchListAndMode(View view) {
        Button mode = (Button) view;
        if (!isListMode) {
            mFragmentManager.beginTransaction().replace(R.id.tab_frame_layout, mListFragment, ListModeFragment.TAG).commit();
            mode.setText(R.string.mode_map_text);
        } else {
            mFragmentManager.beginTransaction().replace(R.id.tab_frame_layout, mMapFragment, MapModeFragment.TAG).commit();
            mode.setText(R.string.mode_list_text);
        }
        isListMode = !isListMode;
    }

    @Override
    public void onSelected(Uri uri) {

    }

    @Override
    public void onAction(Uri uri) {

    }
}
