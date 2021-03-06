package se.my.daik.policheck.screen.main;

import android.arch.lifecycle.LiveData;
import android.util.Log;
import android.view.View;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    private static final String TAG = "PostLiveData";

    private String mFeedUrl = "http://rss.nytimes.com/services/xml/rss/nyt/Politics.xml";
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
                            rssEntryA.getPublishDate();

//                            String someDate = "Thu, 22 Feb 2018 01:37:55";
//                            SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
//                            Date date = sdf.parse(someDate);
//                            System.out.println(date.getTime());

                            Log.d(TAG, "*************** Titel: " + rssEntryA.getTitle());
                            Log.d(TAG, "*************** Description: " + rssEntryA.getDescription());
                            Log.d(TAG, "*************** image: " + rssEntryA.getImage());
                            Log.d(TAG, "*************** link: " + rssEntryA.getLink());
                            Log.d(TAG, "*************** PublishDate: " + rssEntryA.getPublishDate());

                           // Log.d(TAG, "onResponse: FLÖDESTEXTEN " + rssEntryA.getDescription());
                          //  Log.d(TAG, "onResponse: FLÖDESTITELN " + rssEntryA.getTitle());
                          //  Log.d(TAG, "onResponse: FLÖDESBILDEN" + rssEntryA.getImage());

                            newList.add(rssEntry);
                        }

                        for (RssEntry rssEntry : newList) {
                           // Log.d(TAG, "onSuccess: JOHANÄRKING " + rssEntry.getHeadline());
                           // Log.d(TAG, "onSuccess: JOHANÄRCOOL " + rssEntry.getImage());
                            //Log.d(TAG, "onSuccess: JOHANÄRAWESOME " + rssEntry.getMainText());
                        }

                        setValue(newList);

                    }

                    @Override
                    public void onFailure(Call<RssFeed> call, Throwable t) {
                        //Toast.makeText(MainFragment.this, "Failed to fetchRss RSS feed!", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    /**
     * Loads fetched {@link RssItem} list to RecyclerView
     * @param rssItems
     */

}
