package hungrykya.android.example.com.hungrykya.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hungrykya.android.example.com.hungrykya.R;
import hungrykya.android.example.com.hungrykya.adapters.CuisineAdapter;
import hungrykya.android.example.com.hungrykya.adapters.QuickAdapter;
import hungrykya.android.example.com.hungrykya.models.Restaurant;

public class DashboardActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_cuisine_list)
    RecyclerView mCuisineView;
    @BindView(R.id.rv_quick_list)
    RecyclerView mQuickView;

    CuisineAdapter mCuisineAdapter;
    QuickAdapter mQuickAdapter;

    List<Restaurant> mQuickList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        initializeCuisineList();
        initializeQuickList();
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

    private void initializeCuisineList() {
        mCuisineAdapter = new CuisineAdapter();

        LinearLayoutManager cuisineManager =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        mCuisineView.setAdapter(mCuisineAdapter);
        mCuisineView.setLayoutManager(cuisineManager);
    }

    private void initializeQuickList() {

        getQuickListData();

        mQuickAdapter = new QuickAdapter(mQuickList);

        LinearLayoutManager quickManager =
                new LinearLayoutManager(this);

        mQuickView.setAdapter(mQuickAdapter);
        mQuickView.setLayoutManager(quickManager);

    }

    // dummy data. Will be replaced with actual data coming from the Yelp API
    private void getQuickListData() {
        // call the method from rest client
    }
}
