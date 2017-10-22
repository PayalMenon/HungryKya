package hungrykya.android.example.com.hungrykya.yelp;

import com.yelp.fusion.client.connection.YelpFusionApi;
import com.yelp.fusion.client.connection.YelpFusionApiFactory;
import com.yelp.fusion.client.models.SearchResponse;

import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by hyan on 10/22/17.
 */

public class YelpClient {
    private final static String CONSUMER_ID= "enrCHrPA0nHLjLNXuPuOYw";

    private final static String CONSUMER_SECRET= "VOySH79I4Xlbmz6ufOAxM8baphpvNuah1qw093dZIkc5tiPx96l6ohSzfG4xk327";

    private YelpFusionApi yelpAPI;

    private static class InstanceHolder {
        static YelpClient INSTANCE = new YelpClient();
    }

    private  YelpClient() {
        try {
            YelpFusionApiFactory apiFactory = new YelpFusionApiFactory();
            YelpFusionApi yelpFusionApi = apiFactory.createAPI(CONSUMER_ID, CONSUMER_SECRET);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static YelpClient getClient() {
        return InstanceHolder.INSTANCE;
    }

    public void search(HashMap<String, String> queryParams, Callback<SearchResponse> callback) {
        Call<SearchResponse> call = yelpAPI.getBusinessSearch(queryParams);
        call.enqueue(callback);
    }
}
