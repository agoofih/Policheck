package se.my.daik.policheck.screen.main;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import me.toptas.rssconverter.RssConverterFactory;
import me.toptas.rssconverter.RssFeed;
import me.toptas.rssconverter.RssItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.ContentValues.TAG;
import static okhttp3.internal.http.HttpDate.parse;

/**
 * Created by nicklasgilbertson on 2018-02-22.
 */

public class FeedSecondService {

    private String mFeedUrl = "http://feeds.bbci.co.uk/news/politics/rss.xml?edition=uk";
    RssDao rssDao;

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

                            String timeStamp = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").format(Calendar.getInstance().getTime());

                            try {
                                System.out.println(timeStamp);
                                Log.d(TAG, "onResponse: " + timeStamp);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            rssEntryA.setPublishDate(timeStamp);

                            newList.add(rssEntry);
                        }

                        rssEntryRepository.insert(newList);

                    }

                    @Override
                    public void onFailure(Call<RssFeed> call, Throwable t) {

                    }
                });
    }

}