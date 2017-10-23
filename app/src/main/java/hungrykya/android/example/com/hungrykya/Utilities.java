package hungrykya.android.example.com.hungrykya;

import android.util.Base64;

/**
 * Created by hyan on 10/22/17.
 */

public class Utilities {
    public static String base64Id(String userType, String email) {
        String uniqueString = userType + ":" + email;
        return Base64.encodeToString(uniqueString.getBytes(), Base64.DEFAULT);
    }
}
