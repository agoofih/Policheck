package se.my.daik.policheck.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rakangsoftware.tiny.Tiny;
import com.rakangsoftware.tiny.TinyResult;

import java.util.ArrayList;
import java.util.List;

import se.my.daik.policheck.R;
import se.my.daik.policheck.screen.main.MainViewModel;
import se.my.daik.policheck.screen.main.RssAdapter;
import se.my.daik.policheck.screen.main.RssEntry;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    private static final String TAG = "FavoriteFragment";


    private GoToNextFromFavorite goToNextFromFavorite;

    public interface GoToNextFromFavorite {
        void goToNextFragmentFromFavorite();
    }

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        goToNextFromFavorite = (GoToNextFromFavorite) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        goToNextFromFavorite = null;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        RecyclerView favoriteList = view.findViewById(R.id.favorite_list);
        favoriteList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        List<RssEntry> temp = new ArrayList<>();
        RssEntry entry = new RssEntry();
        RssEntry entry2 = new RssEntry();
        RssEntry entry3 = new RssEntry();
        RssEntry entry4 = new RssEntry();
        RssEntry entry5 = new RssEntry();
        RssEntry entry6 = new RssEntry();
        RssEntry entry7 = new RssEntry();

        entry.setHeadline("Testar Headline 1");
        entry.setMainText("Testar brödtext 1... awdawd ad wad adad wa d ad a d ad a d ad a da d a da d a da d ad a dad a da d a da d a da d ad a da d a da d a dadad");
        temp.add(entry);

        entry2.setHeadline("Testar Headline 1");
        entry2.setMainText("Testar brödtext 1... awdawd ad wad adad wa d ad a d ad a d ad a da d a da d a da d ad a dad a da d a da d a da d ad a da d a da d a dadad");
        temp.add(entry2);

        entry3.setHeadline("Testar Headline 1");
        entry3.setMainText("Testar brödtext 1... awdawd ad wad adad wa d ad a d ad a d ad a da d a da d a da d ad a dad a da d a da d a da d ad a da d a da d a dadad");
        temp.add(entry3);

        entry4.setHeadline("Testar Headline 1");
        entry4.setMainText("Testar brödtext 1... awdawd ad wad adad wa d ad a d ad a d ad a da d a da d a da d ad a dad a da d a da d a da d ad a da d a da d a dadad");
        temp.add(entry4);

        entry5.setHeadline("Testar Headline 1");
        entry5.setMainText("Testar brödtext 1... awdawd ad wad adad wa d ad a d ad a d ad a da d a da d a da d ad a dad a da d a da d a da d ad a da d a da d a dadad");
        temp.add(entry5);

        entry6.setHeadline("Testar Headline 1");
        entry6.setMainText("Testar brödtext 1... awdawd ad wad adad wa d ad a d ad a d ad a da d a da d a da d ad a dad a da d a da d a da d ad a da d a da d a dadad");
        temp.add(entry6);

        entry7.setHeadline("Testar Headline 1");
        entry7.setMainText("Testar brödtext 1... awdawd ad wad adad wa d ad a d ad a d ad a da d a da d a da d ad a dad a da d a da d a da d ad a da d a da d a dadad");
        temp.add(entry7);

        RssAdapter adapter = new RssAdapter();
        adapter.setRssEntryList(temp);
        favoriteList.setAdapter(adapter);

    }


}
