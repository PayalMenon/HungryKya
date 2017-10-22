package hungrykya.android.example.com.hungrykya.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import hungrykya.android.example.com.hungrykya.R;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by mengzhou on 10/22/17.
 */

public class MyAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private String[] restaurants;
    private LayoutInflater inflater;

    public MyAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        restaurants = context.getResources().getStringArray(R.array.preferred_locations);
    }

    @Override
    public int getCount() {
        return restaurants.length;
    }

    @Override
    public Object getItem(int position) {
        return restaurants[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            holder.text = (TextView) convertView;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(restaurants[position]);

        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.header, parent, false);
            holder.text = convertView.findViewById(R.id.under_line_text);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        //set header text as first char in name
//        String headerText = "" + restaurants[position].subSequence(0, 1).charAt(0);
        holder.text.setText(restaurants[position]);
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        //return the first character of the country as ID because this is what headers are based upon
        return restaurants[position].subSequence(0, 1).charAt(0);
    }

    class HeaderViewHolder {
        TextView text;
    }

    class ViewHolder {
        TextView text;
    }

}