package hungrykya.android.example.com.hungrykya.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

<<<<<<< HEAD
import com.yelp.fusion.client.models.Business;
=======
import com.bumptech.glide.Glide;
>>>>>>> e0672c8357cd2ce98bc2b12d4323410d6d47f8ed

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hungrykya.android.example.com.hungrykya.R;
import hungrykya.android.example.com.hungrykya.activities.DetailActivity;

public class QuickAdapter extends RecyclerView.Adapter<QuickAdapter.QuickViewHolder>{

<<<<<<< HEAD
    private List<Business> mQuickList;
=======
    private List<Restaurant> mQuickList;
    Context mContext;
>>>>>>> e0672c8357cd2ce98bc2b12d4323410d6d47f8ed

    public QuickAdapter(List<Business> list) {
        this.mQuickList = list;
    }

    @Override
    public QuickViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_quick, parent, false);
        return new QuickViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final QuickViewHolder holder, int position) {
        Business restaurant = mQuickList.get(position);

        holder.hotelTitle.setText(restaurant.getName());
        holder.hotelDistance.setText(String.valueOf(restaurant.getDistance()));
        holder.hotelRating.setText(String.valueOf(restaurant.getRating()));

        Glide.with(mContext)
                .load(restaurant.getImageUrl())
                .into(holder.hotelImage);

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

    public void updateList(List<Restaurant> list) {
        mQuickList = list;
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
