package hungrykya.android.example.com.hungrykya.yelp;

import com.yelp.fusion.client.models.SearchResponse;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import hungrykya.android.example.com.hungrykya.models.Restaurant;
import hungrykya.android.example.com.hungrykya.models.SearchPreference;
import retrofit2.Call;

/**
 * Created by mengzhou on 10/25/17.
 */
public class YelpClientTest {
    @Test
    public void search() throws Exception {
        /*YelpClient yelpClient = YelpClient.getClient();
        SearchPreference preference = new SearchPreference();
        preference.setTerm("restaurants");
        preference.setLatitude(37.4179252);
        preference.setLongitude(-121.9812671);
//        preference.setRadius(10);
//        preference.setOpen_now(true);

        System.out.println("testing started");

        Map<String, String> objectMap = preference.getPreference();
        yelpClient.search(objectMap).subscribe(arrayList -> {
            System.out.println("arrayList: " + (arrayList == null? "null" : arrayList.size()));
        });
        Thread.sleep(5000);
        System.out.println("testing finished");*/
        // 2700 Mission College Blvd, Santa Clara, CA, 95054
    }

}