package hungrykya.android.example.com.hungrykya.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hungrykya.android.example.com.hungrykya.R;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.iv_detail_image)
    ImageView detailsImage;
    @BindView(R.id.tv_detail_title)
    TextView detailsTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        populateViews();
    }

    // dummy data. Will  be replaced with actual data coming from Yelp API
    private void populateViews() {
        detailsImage.setImageResource(R.drawable.indian);
        detailsTitle.setText(R.string.cuisine_indian);
    }
}
