package hungrykya.android.example.com.hungrykya.models;

import android.util.Base64;

import com.google.firebase.database.DatabaseReference;

import hungrykya.android.example.com.hungrykya.Utilities;

/**
 * Created by hyan on 10/22/17.
 */

public class User {
    private final String email;

    private final String name;

    private final String type;

    private String perferenceJSON;

    public User(String email, String name, String type) {
        this.email = email;
        this.name = name;
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getPerferenceJSON() {
        return perferenceJSON;
    }

    public static User fromDatabaseReference(DatabaseReference reference) {
        User user = new User(reference.getKey(), reference.child("name").getKey(),
                reference.child("type").getKey());
        return user;
    }

    public String base64Id() {
        return Utilities.base64Id(type, email);
    }
}
