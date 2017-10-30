package hungrykya.android.example.com.hungrykya.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
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

import com.yelp.fusion.client.models.Business;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hungrykya.android.example.com.hungrykya.R;
import hungrykya.android.example.com.hungrykya.adapters.CuisineAdapter;
import hungrykya.android.example.com.hungrykya.adapters.QuickAdapter;
import hungrykya.android.example.com.hungrykya.models.Restaurant;
import hungrykya.android.example.com.hungrykya.services.YelpService;


public class DashboardActivity extends AppCompatActivity{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_cuisine_list)
    RecyclerView mCuisineView;
    @BindView(R.id.rv_quick_list)
    RecyclerView mQuickView;

    CuisineAdapter mCuisineAdapter;
    QuickAdapter mQuickAdapter;

    List<Business> mQuickList = new ArrayList<>();

    Location mLocation;

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String data = intent.getStringExtra("DATA");
            Gson gson = new Gson();

            mQuickList = gson.fromJson(data, new TypeToken<List<Restaurant>>() {}.getType());

            mQuickAdapter.updateList(mQuickList);
            mQuickAdapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        mLocation = getIntent().getParcelableExtra("CurrentLocation");
        initializeCuisineList();
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver( mReceiver, new IntentFilter("RESPONSELIST"));
        initializeQuickList();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver( mReceiver);
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

                mQuickList.clear();
                mQuickAdapter.updateList(mQuickList);

                HashMap<String, String> paramMap = new HashMap<>();
                paramMap.put("term", query);
                getQuickListData(paramMap);
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

        mQuickAdapter = new QuickAdapter(mQuickList);

        LinearLayoutManager quickManager =
                new LinearLayoutManager(this);

        mQuickView.setAdapter(mQuickAdapter);
        mQuickView.setLayoutManager(quickManager);

        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("latitude", String.valueOf(mLocation.getLatitude()));
        paramMap.put("longitude", String.valueOf(mLocation.getLongitude()));

        getQuickListData(paramMap);

    }

    // dummy data. Will be replaced with actual data coming from the Yelp API
    private void getQuickListData(HashMap<String, String> queryParams) {

        Intent yelpService = new Intent(this, YelpService.class);
        yelpService.putExtra("QueryParams", queryParams);
        startService(yelpService);
    }
}
