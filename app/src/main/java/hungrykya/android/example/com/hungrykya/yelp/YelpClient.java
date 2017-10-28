package hungrykya.android.example.com.hungrykya.yelp;

import com.yelp.fusion.client.connection.YelpFusionApi;
import com.yelp.fusion.client.connection.YelpFusionApiFactory;
import com.yelp.fusion.client.models.Business;
import com.yelp.fusion.client.models.SearchResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
     * @return a Call instance that is cancellable
     */
    public Observable<ArrayList<Business>> search(Map<String, String> queryParams) {
        return yelpAPI.getBusinessSearch(queryParams).subscribeOn(Schedulers.io())
                .map(SearchResponse::getBusinesses).observeOn(AndroidSchedulers.mainThread());
    }
}
