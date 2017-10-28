package hungrykya.android.example.com.hungrykya.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yelp.fusion.client.models.Business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hungrykya.android.example.com.hungrykya.R;
import hungrykya.android.example.com.hungrykya.adapters.CuisineAdapter;
import hungrykya.android.example.com.hungrykya.adapters.QuickAdapter;
import hungrykya.android.example.com.hungrykya.models.SearchPreference;
import hungrykya.android.example.com.hungrykya.yelp.YelpClient;

public class ListModeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    public static final String TAG = ListModeFragment.class.getSimpleName();

    private String mParam1;

    private OnListModeListener mListener;

    private DrawerLayout mDrawer;

    RecyclerView mCuisineView;
    RecyclerView mQuickView;

    CuisineAdapter mCuisineAdapter;
    QuickAdapter mQuickAdapter;

    public ListModeFragment() {
    }

    public static ListModeFragment newInstance(String param1) {
        ListModeFragment fragment = new ListModeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDrawer = view.findViewById(R.id.drawer_layout);
        view.findViewById(R.id.filter_btn).setOnClickListener(listener -> {
            mDrawer.openDrawer(GravityCompat.END);
        });

        mCuisineView = view.findViewById(R.id.rv_cuisine_list);
        mQuickView = view.findViewById(R.id.rv_quick_list);

        initializeCuisineList();
        initializeQuickList();
    }

    private void initializeCuisineList() {
        mCuisineAdapter = new CuisineAdapter();
        mCuisineView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mCuisineView.setAdapter(mCuisineAdapter);
    }

    private void initializeQuickList() {
        YelpClient.getClient().search(getSearch()).subscribe(businesses -> {
            mQuickAdapter = new QuickAdapter(businesses);
            mQuickView.setAdapter(mQuickAdapter);
            mQuickView.setLayoutManager(new LinearLayoutManager(getContext()));
        });
    }

    public Map<String, String> getSearch() {
        SearchPreference preference = new SearchPreference();
        preference.setTerm("restaurants");
        preference.setLatitude(37.4179252);
        preference.setLongitude(-121.9812671);
        return preference.getPreference();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListModeListener) {
            mListener = (OnListModeListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListModeListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListModeListener {
        void onAction(Uri uri);
    }

}
