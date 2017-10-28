package hungrykya.android.example.com.hungrykya.presenter;

import com.yelp.fusion.client.models.Location;
import com.yelp.fusion.client.models.SearchResponse;

import hungrykya.android.example.com.hungrykya.models.SearchPreference;
import hungrykya.android.example.com.hungrykya.yelp.YelpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mengzhou on 10/25/17.
 */

public class MainPresenter {

    public Location getCurrentLocation() {
        return null;
    }

    public void searchRestaurants() {

        SearchPreference preference = new SearchPreference();
        preference.setTerm("restaurants");
        preference.setLatitude(37.4179252);
        preference.setLongitude(-121.9812671);
    }
}