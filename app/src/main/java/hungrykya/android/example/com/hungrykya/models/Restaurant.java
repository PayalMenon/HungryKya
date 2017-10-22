package hungrykya.android.example.com.hungrykya.models;

public class Restaurant {

    private String mImageUrl;
    private String mTitle;
    private String mRating;
    private String mDistance;

    public Restaurant(String imageUrl, String title, String distance, String rating) {
        this.mImageUrl = imageUrl;
        this.mTitle = title;
        this.mRating = rating;
        this.mDistance = distance;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getRating() {
        return mRating;
    }

    public String getDistance() {
        return mDistance;
    }
}
