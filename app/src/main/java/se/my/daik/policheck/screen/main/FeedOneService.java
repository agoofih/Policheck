package se.my.daik.policheck.screen.main;

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
 * Created by nicklasgilbertson on 2018-02-22.
 */

public class FeedOneService {

    private String mFeedUrl = "http://rss.nytimes.com/services/xml/rss/nyt/Politics.xml";

    public void update(final RssEntryRepository rssEntryRepository) {
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


                            newList.add(rssEntry);
                        }

                        rssEntryRepository.insert(newList);

                    }

                    @Override
                    public void onFailure(Call<RssFeed> call, Throwable t) {
                        //Toast.makeText(MainFragment.this, "Failed to fetchRss RSS feed!", Toast.LENGTH_SHORT).show();

                    }
                });
    }

}
