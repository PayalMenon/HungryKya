package hungrykya.android.example.com.hungrykya.adapters;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hungrykya.android.example.com.hungrykya.R;
import hungrykya.android.example.com.hungrykya.activities.DetailActivity;
import hungrykya.android.example.com.hungrykya.models.Restaurant;

public class QuickAdapter extends RecyclerView.Adapter<QuickAdapter.QuickViewHolder>{

    private List<Restaurant> mQuickList;

    public QuickAdapter(List<Restaurant> list) {
        this.mQuickList = list;
    }

    @Override
    public QuickViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_quick, parent, false);
        return new QuickViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final QuickViewHolder holder, int position) {
        Restaurant restaurant = mQuickList.get(position);

        holder.hotelTitle.setText(restaurant.getTitle());
        holder.hotelDistance.setText(restaurant.getDistance());
        holder.hotelRating.setText(restaurant.getRating());

        holder.hotelContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mQuickList.size();
    }

    public class QuickViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_quick_image)
        ImageView hotelImage;
        @BindView(R.id.tv_quick_title)
        TextView hotelTitle;
        @BindView(R.id.tv_quick_distance)
        TextView hotelDistance;
        @BindView(R.id.tv_quick_rating)
        TextView hotelRating;
        @BindView(R.id.cv_quick_card)
        CardView hotelContainer;

        public QuickViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
