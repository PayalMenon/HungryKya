package hungrykya.android.example.com.hungrykya.yelp;

import org.junit.Test;

import hungrykya.android.example.com.hungrykya.models.SearchPreference;

/**
 * Created by mengzhou on 10/25/17.
 */
public class YelpClientTest {
    @Test
    public void search() throws Exception {
        YelpClient yelpClient = YelpClient.getClient();
        SearchPreference preference = new SearchPreference();
        preference.setTerm("restaurants");
        preference.setLatitude(37.4179252);
        preference.setLongitude(-121.9812671);
//        preference.setRadius(10);
//        preference.setOpen_now(true);

        System.out.println("testing started");

        /*Map<String, String> objectMap = preference.getPreference();
        yelpClient.search(objectMap, new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                System.out.println("testing succeed");
                System.out.println(response.body().getTotal());
                List<Restaurant> restaurants = Restaurant.restaurantsFromResponse(response.body());
                System.out.println(restaurants == null ? "get null " : "size: " + restaurants.size());
                assert restaurants != null;
                for (Restaurant restaurant : restaurants) {
                    System.out.println(restaurant.toString());
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                System.out.println("testing failed");
                System.out.println(t.getMessage());
            }
        });*/

        Thread.sleep(2000);
        System.out.println("testing finished");
    }

}