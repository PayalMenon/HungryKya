package hungrykya.android.example.com.hungrykya.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hungrykya.android.example.com.hungrykya.R;

public class CuisineAdapter extends RecyclerView.Adapter<CuisineAdapter.CuisineViewHolder> {

    Integer[] mCuisineList = {
            R.drawable.american,
            R.drawable.italian,
            R.drawable.mexican,
            R.drawable.indian,
            R.drawable.chinese};

    Integer[] mCuisineNames = {
            R.string.cuisine_american,
            R.string.cuisine_italian,
            R.string.cuisine_mexican,
            R.string.cuisine_indian,
            R.string.cuisine_chinese};

    @Override
    public CuisineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_cuisine, parent, false);
        return new CuisineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CuisineViewHolder holder, int position) {
        holder.cuisineImage.setImageResource(mCuisineList[position]);
        holder.cuisineTitle.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return mCuisineList.length;
    }

    public class CuisineViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_cuisine_image)
        ImageView cuisineImage;
        @BindView(R.id.tv_cuisine_title)
        TextView cuisineTitle;
        @BindView(R.id.tv_cuisine_distance)
        TextView cuisineDistance;
        @BindView(R.id.tv_cuisine_rating)
        TextView cuisineRating;
        @BindView(R.id.cv_cuisine_container)
        CardView cuisineContainer;

        public CuisineViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
