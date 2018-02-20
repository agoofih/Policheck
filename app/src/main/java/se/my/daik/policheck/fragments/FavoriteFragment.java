package se.my.daik.policheck.fragments;


import android.arch.lifecycle.Observer;
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
import android.widget.Toast;

//import com.rakangsoftware.tiny.Tiny;
//import com.rakangsoftware.tiny.TinyResult;

import java.util.ArrayList;
import java.util.List;

import se.my.daik.policheck.R;
import se.my.daik.policheck.screen.main.MainViewModel;
import se.my.daik.policheck.screen.main.RssAdapter;
import se.my.daik.policheck.screen.main.RssEntry;
import se.my.daik.policheck.screen.main.RssEntryRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment implements RssAdapter.OnFavBtnClickedListener {

    private static final String TAG = "FavoriteFragment";


    private GoToNextFromFavorite goToNextFromFavorite;
    private MainViewModel mViewModel;
    private RssEntryRepository rssEntryRepository;

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

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        RecyclerView favoriteList = view.findViewById(R.id.favorite_list);
        favoriteList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        final RssAdapter adapter = new RssAdapter(this);
        favoriteList.setAdapter(adapter);

            mViewModel.getmList().observe(this, new Observer<List<RssEntry>>() {
            @Override
            public void onChanged(@Nullable List<RssEntry> rssEntries) {

                Log.d(TAG, "onChanged: " + rssEntries);

                adapter.setRssEntryList(rssEntries);
            }
        });

    }

    @Override
    public void onFavBtnClicked(RssEntry rssEntry) {
        Log.d(TAG, "onFavBtnClicked: asdsadasdada");
        mViewModel.removeItem(rssEntry);
    }

}
