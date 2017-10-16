package hungrykya.android.example.com.hungrykya.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import hungrykya.android.example.com.hungrykya.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Dummy Timer, will be relaced with ASync Task returning locaton
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (Exception e) {
                    Log.d("Hungrykya", e.toString());
                } finally {
                    Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
    }
}
