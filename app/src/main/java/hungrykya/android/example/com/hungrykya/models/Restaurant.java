package hungrykya.android.example.com.hungrykya.models;

import com.yelp.fusion.client.models.Business;
import com.yelp.fusion.client.models.Location;
import com.yelp.fusion.client.models.SearchResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class Restaurant {
    private String mId;

    private String mTitle;

    private String mImageUrl;

    private Double mRating;

    private Double mDistance;

    private boolean closed;

    private Location mLocation;

    private List<String> photos;

    public Restaurant(){}

    public Restaurant(String imageUrl, String title, Double distance, Double rating) {
        this.mImageUrl = imageUrl;
        this.mTitle = title;
        this.mRating = rating;
        this.mDistance = distance;
    }


    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = mTitle;
    }

    public Double getRating() {
        return mRating;
    }

    public void setRating(Double rating) {
        this.mRating = rating;
    }

    public Double getDistance() {
        return mDistance;
    }

    public void setDistance(Double distance) {
        this.mDistance = distance;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public Location getLocation() {
        return mLocation;
    }

    public void setLocation(Location location) {
        this.mLocation = location;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    /**
     * Get restaurant from business
     * @param business
     * @return
     */
    private static Restaurant fromBusiness(Business business) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(business.getId());
        restaurant.setTitle(business.getName());
        restaurant.setImageUrl(business.getImageUrl());
        restaurant.setRating(business.getRating());
        restaurant.setDistance(business.getDistance());
        restaurant.setClosed(business.getIsClosed());
        restaurant.setLocation(business.getLocation());
        restaurant.setPhotos(business.getPhotos());
        return restaurant;
    }

    /**
     * Get A Restaurant instance from call back.
     * @param response a response instance
     * @return
     */
    public static Restaurant restaurantFromResponse(Response<Business> response) {
        return fromBusiness(response.body());
    }

    /**
     * Get Restaurant list from call back.
     * @param response a response instance
     * @return
     */
    public static List<Restaurant> restaurantsFromResponse(SearchResponse response) {
        List<Restaurant> restaurants = new ArrayList<>();

        ArrayList<Business> businesses = response.getBusinesses();
        for (Business business: businesses) {
            restaurants.add(fromBusiness(business));
        }
        return restaurants;
    }
}
