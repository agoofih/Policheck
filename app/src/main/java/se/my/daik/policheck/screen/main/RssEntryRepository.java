package se.my.daik.policheck.screen.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by daik on 2018-02-13.
 */

public class RssEntryRepository {

    private LiveData<List<RssEntry>> mEntries = new PostLiveData();
    private LiveData<List<RssEntry>> yEntries = new PostLiveDataExpressen();


    private RssDao rssDao;

    private Executor IO = Executors.newSingleThreadExecutor();

    public RssEntryRepository(Context context) {
        rssDao = PolicheckDatabase.getInstance(context).getRssEntryDao();
    }

    //API
    public LiveData<List<RssEntry>> getmEntries() {
        return mEntries;
    }

    public LiveData<List<RssEntry>> getyEntries() {
        return yEntries;
    }

    //ROOM
    public LiveData<List<RssEntry>> getAll() {
        return rssDao.read();
    }

    public LiveData<List<RssEntry>> getFav() {
        return rssDao.readFav(true);
    }

    public void removeItem(final RssEntry rssEntry) {
        IO.execute(new Runnable() {
            @Override
            public void run() {
                rssDao.delete(rssEntry);
            }
        });
    }

    public void insert(final List<RssEntry> newList) {
        final RssEntry[] arr = newList.toArray(new RssEntry[newList.size()]);
        IO.execute(new Runnable() {
            @Override
            public void run() {
                rssDao.insert(arr);
            }
        });
    }

    public void update(final RssEntry rssEntry) {
        IO.execute(new Runnable() {
            @Override
            public void run() {
                rssDao.update(rssEntry);
            }
        });
    }

    public void nuke() {
        IO.execute(new Runnable() {
            @Override
            public void run() {
                rssDao.nukeTable();
            }
        });
    }


//    private static class ReadDatabaseAsyncTask extends AsyncTask<Void,Void,List<RssEntry>> {
//
//
//        private final RssDao rssDao;
//        private final MutableLiveData<List<RssEntry>> mList;
//
//        public ReadDatabaseAsyncTask(RssDao rssDao, MutableLiveData<List<RssEntry>> mList) {
//            this.rssDao = rssDao;
//            this.mList = mList;
//        }
//
//        @Override
//        protected List<RssEntry> doInBackground(Void... voids) {
//                /*List<RssEntry> tempFav = new ArrayList<>();
//                RssEntry entry = new RssEntry();
//                RssEntry entry2 = new RssEntry();
//                RssEntry entry3 = new RssEntry();
//
//                entry.setHeadline("Testar JOhan 1");
//                entry.setMainText("Testar brödtext 1... awdawd ad wad adad wa d ad a d ad a d ad a da d a da d a da d ad a dad a da d a da d a da d ad a da d a da d a dadad");
//                tempFav.add(entry);
//
//                entry2.setHeadline("Testar Heasadsadline 1");
//                entry2.setMainText("Testar brödtext 1... awdawd ad wad adad wa d ad a d ad a d ad a da d a da d a da d ad a dad a da d a da d a da d ad a da d a da d a dadad");
//                tempFav.add(entry2);
//
//                entry3.setHeadline("Testar hejhej 1");
//                entry3.setMainText("Testar brödtext 1... awdawd ad wad adad wa d ad a d ad a d ad a da d a da d a da d ad a dad a da d a da d a da d ad a da d a da d a dadad");
//                tempFav.add(entry3);
//
//                for (RssEntry rssEntry : tempFav) {
//                    rssDao.insert(rssEntry);
//                }*/
//            return rssDao.read();
//        }
//
//        @Override
//        protected void onPostExecute(List<RssEntry> rssEntries) {
//            mList.setValue(rssEntries);
//        }
//    }

}
