package se.my.daik.policheck.screen.main;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicklasgilbertson on 2018-02-15.
 */

public class PostLiveDataExpressen  extends LiveData<List<RssEntry>> {

    private static final String TAG = "PostLiveDataExpressen";
    /*public PostLiveDataExpressen() {
        Tiny.fetch("https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fwww.expressen.se%2Frss%2Fdebatt&api_key=sjnpgckj5ubjydnrseqaalzvtranvp9rxhviexy8&order_by=title&count=30", RSSResult.class).get(new TinyResult<RSSResult>() {

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
}
