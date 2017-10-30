package hungrykya.android.example.com.hungrykya.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.yelp.fusion.client.models.Business;
import com.yelp.fusion.client.models.Location;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hungrykya.android.example.com.hungrykya.R;
import hungrykya.android.example.com.hungrykya.adapters.CuisineAdapter;
import hungrykya.android.example.com.hungrykya.models.SearchPreference;
import hungrykya.android.example.com.hungrykya.yelp.YelpClient;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MapModeFragment extends Fragment implements OnMapReadyCallback {

    private static final String ARG_PARAM1 = "param1";
    public static final String TAG = MapModeFragment.class.getSimpleName();

    private String mParam1;
    private GoogleMap mMap;

    private OnMapModeListener mListener;
    private RecyclerView mRecyclerView;
    private DrawerLayout mDrawer;
    private LocationManager mLocationManager;

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

        mLocationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
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


        mMap.setOnMarkerClickListener(marker -> {
            mRecyclerView.setVisibility(View.VISIBLE);
            return false;
        });
        mMap.setOnMapClickListener(mListener -> {
            mRecyclerView.setVisibility(View.GONE);
        });

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Toast.makeText(getContext(), "get permission", Toast.LENGTH_SHORT).show();
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.setOnMyLocationButtonClickListener(() -> {
            Toast.makeText(getContext(), "onMyLocationButtonClick", Toast.LENGTH_SHORT).show();
            return false;
        });
        mMap.setOnMyLocationClickListener(location -> {
            Toast.makeText(getContext(), location.getLatitude() + ", " + location.getLongitude(), Toast.LENGTH_SHORT).show();
        });
        mMap.setOnMapClickListener(latLng ->  {
            Toast.makeText(getContext(), latLng.latitude + ", " + latLng.longitude, Toast.LENGTH_SHORT).show();
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        });


        YelpClient.getClient().search(getSearch()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(businesses -> {
                    for(Business business : businesses) {
                        drawMarker(business.getLocation());
                    }
                });
    }

    public Map<String, String> getSearch() {
        SearchPreference preference = new SearchPreference();
        preference.setTerm("restaurants");
        preference.setLatitude(37.4179252);
        preference.setLongitude(-121.9812671);
        return preference.getPreference();
    }

    private void drawMarker(Location location) {
        if (mMap != null && location != null) {
            mMap.clear();
            StringBuilder address = new StringBuilder();
            address.append(location.getAddress1() + ", ");
            address.append(location.getCity() + ", ");
            address.append(location.getState() + ", ");
            address.append(location.getZipCode());
            LatLng latLng = getLocationFromAddress(getContext(), address.toString());
            if(latLng == null) return;
            mMap.addMarker(new MarkerOptions().position(latLng).title(location.getDisplayAddress().toString()));
        }
    }

    public LatLng getLocationFromAddress(Context context, String strAddress) {
        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p1;

    }
}
