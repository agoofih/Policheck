package se.my.daik.policheck.screen.main;

import android.arch.lifecycle.LiveData;
import android.util.Log;
import android.view.View;

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

public class PostLiveData extends LiveData<List<RssEntry>> {

    //API CALL: https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fwww.dagenssamhalle.se%2Ffeed%2Fall
    private static final String TAG = "PostLiveData";
   /* public PostLiveData() {

        Tiny.fetch("https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fwww.dagenssamhalle.se%2Ffeed%2Fall&api_key=sjnpgckj5ubjydnrseqaalzvtranvp9rxhviexy8&order_by=title&count=30", RSSResult.class).get(new TinyResult<RSSResult>() {

            @Override
            public void onSuccess(final RSSResult post) {

                List<RssEntry> newList = new ArrayList<>();

                for (RssEntryA rssEntryA : post.getItems()) {

                    RssEntry rssEntry = new RssEntry();
                    rssEntry.setHeadline(rssEntryA.getTitle());
                    rssEntry.setMainText(rssEntryA.getDescription());

                    newList.add(rssEntry);
                }

                for (RssEntry rssEntry : newList) {
                    Log.d(TAG, "onSuccess: JOHAN " + rssEntry.getHeadline());
                }

                setValue(newList);
            }

            @Override
            public void onFail(final Throwable throwable) {
                Log.d(TAG, "onFail() called with: throwable = [" + throwable + "]");

            }
        });
    }*/


    /*public PostLiveData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://github.com")
                .addConverterFactory(RssConverterFactory.create())
                .build();

        RssService service = retrofit.create(RssService.class);
        service.getRss("https://www.expressen.se/rss/debatt")
                .enqueue(new Callback<RssFeed>() {

                    @Override
                    public void onResponse(Call<RssFeed> call, Response<RssFeed> response) {
                        // Populate list with response.body().getItems()
                        response.body().getItems();

                        List<RssEntry> newList = new ArrayList<>();

                        for (RssEntryA rssEntryA : response.body().getItems()) {

                            RssEntry rssEntry = new RssEntry();
                            rssEntry.setHeadline(rssEntryA.getTitle());
                            rssEntry.setMainText(rssEntryA.getDescription());

                            newList.add(rssEntry);
                        }

                    }

                    @Override
                    public void onFailure(Call<RssFeed> call, Throwable t) {
                        // Show failure message
                    }
                });
    }*/

    private String mFeedUrl = "http://www.aftonbladet.se/debatt/rss.xml";
    private RssAdapter mAdapter;


    public PostLiveData() {
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

    /*public PostLiveData() {
        Tiny.fetch("https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fwww.dagenssamhalle.se%2Ffeed%2Fall&api_key=sjnpgckj5ubjydnrseqaalzvtranvp9rxhviexy8&order_by=title&count=30", RSSResult.class).get(new TinyResult<RSSResult>() {

            @Override
            public void onSuccess(final RSSResult post) {

                List<RssEntry> newList = new ArrayList<>();

                for (RssEntryA rssEntryA : post.getItems()) {

                    RssEntry rssEntry = new RssEntry();
                    rssEntry.setHeadline(rssEntryA.getTitle());
                    rssEntry.setMainText(rssEntryA.getDescription());

                    newList.add(rssEntry);
                }

                for (RssEntry rssEntry : newList) {
                    Log.d(TAG, "onSuccess: JOHAN " + rssEntry.getHeadline());
                }

                setValue(newList);
            }

            @Override
            public void onFail(final Throwable throwable) {
                Log.d(TAG, "onFail() called with: throwable = [" + throwable + "]");

            }
        });
    }*/

    /**
     * Loads fetched {@link RssItem} list to RecyclerView
     * @param rssItems
     */

}
