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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import hungrykya.android.example.com.hungrykya.R;
import hungrykya.android.example.com.hungrykya.adapters.CuisineAdapter;

public class MapModeFragment extends Fragment implements OnMapReadyCallback {

    private static final String ARG_PARAM1 = "param1";
    public static final String TAG = MapModeFragment.class.getSimpleName();

    private String mParam1;
    private GoogleMap mMap;

    private OnMapModeListener mListener;
    private RecyclerView mRecyclerView;
    private DrawerLayout mDrawer;

    public MapModeFragment() {
    }

    public static MapModeFragment newInstance(String param1) {
        MapModeFragment fragment = new MapModeFragment();
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
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDrawer = view.findViewById(R.id.drawer_layout);
        view.findViewById(R.id.filter_btn).setOnClickListener(listener -> {
            mDrawer.openDrawer(GravityCompat.END);
        });

        mRecyclerView = view.findViewById(R.id.recycler_view_horizontal_restaurant);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(new CuisineAdapter());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMapModeListener) {
            mListener = (OnMapModeListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnMapModeListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnMapModeListener {
        void onSelected(Uri uri);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.setOnMarkerClickListener(marker -> {
            mRecyclerView.setVisibility(View.VISIBLE);
            return false;
        });
        mMap.setOnMapClickListener(mListener -> {mRecyclerView.setVisibility(View.GONE);});
    }
}
