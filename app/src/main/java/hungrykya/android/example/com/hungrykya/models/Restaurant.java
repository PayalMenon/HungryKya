package hungrykya.android.example.com.hungrykya.models;

public class Restaurant {

    private Integer mImageUrl;
    private Integer mTitle;

    public Restaurant(Integer imageUrl, Integer title) {
        this.mImageUrl = imageUrl;
        this.mTitle = title;
    }

    public Integer getImageUrl() {
        return mImageUrl;
    }

    public Integer getTitle() {
        return mTitle;
    }
}
