package hungrykya.android.example.com.hungrykya.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import hungrykya.android.example.com.hungrykya.Utilities;
import hungrykya.android.example.com.hungrykya.models.User;

/**
 * Created by hyan on 10/22/17.
 */

public class FirebaseClient {
    private static final String TAG = "FirebaseClient";

    private DatabaseReference mDatabase;

    private FirebaseAuth mAuth;

    public static FirebaseClient getClient() {
        return InstanceHolder.INSTANCE;
    }

    private static class InstanceHolder{
        static FirebaseClient INSTANCE = new FirebaseClient();
    }

    private FirebaseClient() {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();
    }

    public User getUser(String type, String email) {
        DatabaseReference reference = mDatabase.child("users").child(Utilities.base64Id(type, email));
        if (reference == null) {
            return null;
        }
        return User.fromDatabaseReference(reference);
    }

    public void writeUser(User user) {
        DatabaseReference reference = mDatabase.child("users");
        Map<String, Object> users = new HashMap<>();
        users.put(user.base64Id(), user);
        reference.updateChildren(users);
    }
}
