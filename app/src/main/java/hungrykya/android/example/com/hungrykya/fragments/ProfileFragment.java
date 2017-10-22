package hungrykya.android.example.com.hungrykya.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hungrykya.android.example.com.hungrykya.R;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by mengzhou on 10/22/17.
 */

public class ProfileFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    public static final String TAG = PreferenceFragment.class.getSimpleName();

    private String mParam1;

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance(String param1) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
