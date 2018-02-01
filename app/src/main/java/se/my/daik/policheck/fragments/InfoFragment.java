package se.my.daik.policheck.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import se.my.daik.policheck.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {

    private static final String TAG = "InfoFragment";

    private GoToNextFromInfo goToNextFromInfo;

    public interface GoToNextFromInfo {
        void goToNextFragmentFromInfo();
    }

    public InfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        goToNextFromInfo = (GoToNextFromInfo) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        goToNextFromInfo = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

}
