package se.my.daik.policheck.screen.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by daik on 2018-02-13.
 */

public class RssEntryRepository {

    private MutableLiveData<List<RssEntry>> entries = new MutableLiveData<>();

    private RssDao rssDao;

    private Executor IO = Executors.newSingleThreadExecutor();

    public RssEntryRepository(Context context) {

        PolicheckDatabase database = Room.databaseBuilder(context, PolicheckDatabase.class, "poli-check-database.db").build();

        rssDao = database.getRssEntryDao();

        refresh();
    }


    public void refresh(){
        //new ReadDatabaseAsyncTask(rssDao, entries).execute();


    }

    public LiveData<List<RssEntry>> getAll() {
        return rssDao.read();
    }

    public void removeItem(final RssEntry rssEntry) {
        IO.execute(new Runnable() {
            @Override
            public void run() {
                rssDao.delete(rssEntry);
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
