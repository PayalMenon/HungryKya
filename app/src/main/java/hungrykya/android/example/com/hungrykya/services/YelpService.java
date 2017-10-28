package hungrykya.android.example.com.hungrykya.services;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.yelp.fusion.client.models.SearchResponse;

import java.util.HashMap;
import java.util.List;

import hungrykya.android.example.com.hungrykya.models.Restaurant;
import hungrykya.android.example.com.hungrykya.yelp.YelpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YelpService extends IntentService {

    public YelpService() {
        super("YelpService");
    }

    public YelpService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        HashMap<String, String> paramMap = (HashMap<String, String>) intent.getSerializableExtra("QueryParams");

        YelpClient client = YelpClient.getClient();

        /*client.search(paramMap, new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if(response.isSuccessful()) {
                    List<Restaurant> list = Restaurant.restaurantsFromResponse(response.body());
                    Gson gson = new Gson();
                    String data = gson.toJson(list);
                    Intent intent = new Intent("RESPONSELIST");
                    intent.putExtra("DATA", data);
                    sendBroadcast(intent);
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {

            }
        });*/

    }
}
