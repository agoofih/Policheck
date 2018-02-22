package se.my.daik.policheck.screen.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import se.my.daik.policheck.Factory;

/**
 * Created by daik on 2018-02-01.
 */

public class MainViewModel extends AndroidViewModel {

    private static final String TAG = "MainViewModel";

    private RssEntryRepository rssEntryRepository;

    public MainViewModel(@NonNull final Application application) {
        super(application);

        Log.d(TAG, "MainViewModel: ");

        rssEntryRepository = Factory.getRssEntryRepository(this.getApplication());

        //new FeedOneService().update(rssEntryRepository);
    }


    public LiveData<List<RssEntry>> getCompleteList() {
        return rssEntryRepository.getAll();
    }

    public LiveData<List<RssEntry>> getFavList() {
        return rssEntryRepository.getFav();
    }


    //API
    /*public LiveData<List<RssEntry>> getMEntriesFromApi() {
        return rssEntryRepository.getCompleteList();
    }*/


    //ROOM
    public void removeItem(RssEntry rssEntry) {
        rssEntryRepository.removeItem(rssEntry);
    }

    public LiveData<List<RssEntry>> getmList() {
        return rssEntryRepository.getAll();
    }

    public void update(RssEntry rssEntry) {
        rssEntryRepository.update(rssEntry);
    }
}
