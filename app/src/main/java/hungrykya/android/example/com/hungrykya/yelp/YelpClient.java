package hungrykya.android.example.com.hungrykya.yelp;

import com.yelp.fusion.client.connection.YelpFusionApi;
import com.yelp.fusion.client.connection.YelpFusionApiFactory;
import com.yelp.fusion.client.models.SearchResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A YELP Client to fetch related data
 *
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
            yelpAPI = apiFactory.createAPI(CONSUMER_ID, CONSUMER_SECRET);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static YelpClient getClient() {
        return InstanceHolder.INSTANCE;
    }

    /**
     * Asynchronous Requests Call to search all businesses
     * see https://www.yelp.com/developers/documentation/v3/business_search
     *
     * Here is an example how you can search
     * general params
     * params.put("term", "indian food");
     * params.put("latitude", "40.581140");
     * params.put("longitude", "-111.914184");
     *
     * @param queryParams A query string map
     * @param callback a callback handle the search
     * @return a Call instance that is cancellable
     */
    public Call<SearchResponse>  search(Map<String, String> queryParams, Callback<SearchResponse> callback) {
        Call<SearchResponse> call = yelpAPI.getBusinessSearch(queryParams);
        call.enqueue(callback);
        return call;
    }

    /**
     * Synchronous Requests Call to search all businesses
     * see https://www.yelp.com/developers/documentation/v3/business_search
     *
     * Here is an example how you can search
     * general params
     * params.put("term", "indian food");
     * params.put("latitude", "40.581140");
     * params.put("longitude", "-111.914184");
     *
     * @param queryParams
     * @return a response instance that can retrieve business
     * @throws IOException if any network problem occurs
     */
    public Response<SearchResponse> search(Map<String, String> queryParams) throws IOException {
        Call<SearchResponse> call = yelpAPI.getBusinessSearch(queryParams);
        return call.execute();
    }
}
