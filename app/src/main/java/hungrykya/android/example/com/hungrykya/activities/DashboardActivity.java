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
        setContentView(R.layout.activity_dashboard);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_filter) {

            return true;
        } else if (item.getItemId() == R.id.action_map) {

            return true;
        }

        return super.onOptionsItemSelected(item);
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
        Integer[] mCuisineList = {R.drawable.american,
                R.drawable.italian,
                R.drawable.mexican,
                R.drawable.indian,
                R.drawable.chinese,
                R.drawable.mediterrinian,
                R.drawable.others};

        Integer[] mCuisineNames = {R.string.cuisine_american,
                R.string.cuisine_italian,
                R.string.cuisine_mexican,
                R.string.cuisine_indian,
                R.string.cuisine_chinese,
                R.string.cuisine_mediterranean,
                R.string.cuisine_other};

        for (int i = 0; i < mCuisineList.length; i++) {
            Integer image = mCuisineList[i];
            Integer name = mCuisineNames[i];
            Restaurant restaurant = new Restaurant(image, name);
            mQuickList.add(restaurant);
        }
    }
}
