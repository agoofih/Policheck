package se.my.daik.policheck.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import se.my.daik.policheck.R;
import se.my.daik.policheck.screen.main.MainViewModel;
import se.my.daik.policheck.screen.main.RssAdapter;
import se.my.daik.policheck.screen.main.RssEntry;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";

    private GoToNextFromMain goToNextFromMain;

    public interface GoToNextFromMain {
        void goToNextFragmentFromMain();
    }

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        goToNextFromMain = (GoToNextFromMain) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        goToNextFromMain = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        RecyclerView mainList = view.findViewById(R.id.main_list);
        mainList.setLayoutManager(new LinearLayoutManager(view.getContext()));



    }
}
