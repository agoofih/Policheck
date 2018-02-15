package se.my.daik.policheck.screen.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daik on 2018-02-01.
 */

public class MainViewModel extends AndroidViewModel {

    private static final String TAG = "MainViewModel";

    private RssEntryRepository rssEntryRepository;

    private MutableLiveData<List<RssEntry>> mList = new MutableLiveData<>();

    public MainViewModel(@NonNull final Application application) {
        super(application);

        Log.d(TAG, "MainViewModel: ");

        rssEntryRepository = new RssEntryRepository(this.getApplication());

        refresh();
        
    }

    public void refresh() {
        rssEntryRepository.refresh();
    }

    /*public void removeItem(RssEntry rssEntry) {

        List<RssEntry> newList;
        newList = mList.getValue();
        newList.remove(rssEntry);
        mList.setValue(newList);

    }*/

    public LiveData<List<RssEntry>> getmList() {
        return rssEntryRepository.getAll();
    }


}
