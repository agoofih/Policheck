package se.my.daik.policheck.screen.main;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import me.toptas.rssconverter.RssConverterFactory;
import me.toptas.rssconverter.RssFeed;
import me.toptas.rssconverter.RssItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by nicklasgilbertson on 2018-02-15.
 */

public class PostLiveDataExpressen  extends LiveData<List<RssEntry>> {

    private static final String TAG = "PostLiveDataExpressen";

    private String mFeedUrl = "http://feeds.bbci.co.uk/news/politics/rss.xml?edition=uk";
    private RssAdapter mAdapter;


    public PostLiveDataExpressen() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://github.com")
                .addConverterFactory(RssConverterFactory.create())
                .build();

        RssService service = retrofit.create(RssService.class);
        service.getRss(mFeedUrl)
                .enqueue(new Callback<RssFeed>() {
                    @Override
                    public void onResponse(Call<RssFeed> call, Response<RssFeed> response) {
                        //onRssItemsLoaded(response.body().getItems());

                        List<RssEntry> newList = new ArrayList<>();

                        for (RssItem rssEntryA : response.body().getItems()) {

                            RssEntry rssEntry = new RssEntry();
                            rssEntry.setHeadline(rssEntryA.getTitle());
                            rssEntry.setMainText(rssEntryA.getDescription());
                            rssEntry.setImage(rssEntryA.getImage());

                            Log.d(TAG, "onResponse: FLÖDESTEXTEN " + rssEntryA.getDescription());
                            Log.d(TAG, "onResponse: FLÖDESTITELN " + rssEntryA.getTitle());
                            Log.d(TAG, "onResponse: FLÖDESBILDEN" + rssEntryA.getImage());

                            newList.add(rssEntry);
                        }

                        for (RssEntry rssEntry : newList) {
                            Log.d(TAG, "onSuccess: JOHANÄRKING " + rssEntry.getHeadline());
                            Log.d(TAG, "onSuccess: JOHANÄRCOOL " + rssEntry.getImage());
                            Log.d(TAG, "onSuccess: JOHANÄRAWESOME " + rssEntry.getMainText());
                        }

                        setValue(newList);

                    }

                    @Override
                    public void onFailure(Call<RssFeed> call, Throwable t) {
                        //Toast.makeText(MainFragment.this, "Failed to fetchRss RSS feed!", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}
