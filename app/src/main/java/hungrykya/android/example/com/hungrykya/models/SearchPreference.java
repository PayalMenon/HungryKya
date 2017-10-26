package hungrykya.android.example.com.hungrykya.models;

import com.yelp.fusion.client.models.Location;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hyan on 10/22/17.
 */

public class SearchPreference {

    public static String TERM = "term";
    public static String LOCATION = "location";
    public static String LATITUDE = "latitude";
    public static String LONGITUDE = "longitude";
    public static String RADIUS = "radius";
    public static String CATEGORIES = "categories";
    public static String LOCALE = "locale";
    public static String LIMIT = "limit";
    public static String OFFSET = "offset";
    public static String PRICE = "price";
    public static String OPEN_NOW = "open_now";
    public static String OPEN_AT = "open_at";
    public static String ATTRIBUTES = "attributes";
    public static String SORT_BY = "sort_by";


    private  String   term;
    private  Location location;
    private  double   latitude;
    private  double   longitude;
    private  int      radius;
    private  String   categories;
    private  String   locale;
    private  int      limit;
    private  int      offset;
    private  String   price;
    private  boolean  open_now;
    private  int      open_at;
    private  String   attributes;
    private  String   sort_by;

    Map<String, String> mPreference = new HashMap<>();


    public Map<String, String> getPreference() {
        return mPreference;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        mPreference.put(TERM, term);
        this.term = term;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        mPreference.put(LOCATION, location.toString());
        this.location = location;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        mPreference.put(LATITUDE, String.valueOf(latitude));
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        mPreference.put(LONGITUDE, String.valueOf(longitude));
        this.longitude = longitude;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        mPreference.put(RADIUS, String.valueOf(radius));
        this.radius = radius;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        mPreference.put(CATEGORIES, categories);
        this.categories = categories;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        mPreference.put(LOCALE, locale);
        this.locale = locale;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        mPreference.put(LIMIT, String.valueOf(limit));
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        mPreference.put(OFFSET, String.valueOf(offset));
        this.offset = offset;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        mPreference.put(PRICE, price);
        this.price = price;
    }

    public boolean isOpen_now() {
        return open_now;
    }

    public void setOpen_now(boolean open_now) {
        mPreference.put(OPEN_NOW, String.valueOf(open_now));
        this.open_now = open_now;
    }

    public int getOpen_at() {
        return open_at;
    }

    public void setOpen_at(int open_at) {
        mPreference.put(OPEN_AT, String.valueOf(open_at));
        this.open_at = open_at;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        mPreference.put(ATTRIBUTES, attributes);
        this.attributes = attributes;
    }

    public String getSort_by() {
        return sort_by;
    }

    public void setSort_by(String sort_by) {
        mPreference.put(SORT_BY, sort_by);
        this.sort_by = sort_by;
    }
}
