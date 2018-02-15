package se.my.daik.policheck.screen.main;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.rakangsoftware.tiny.Tiny;
import com.rakangsoftware.tiny.TinyResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicklasgilbertson on 2018-02-15.
 */

public class PostLiveData extends LiveData<List<RssEntry>> {

    //API CALL: https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fwww.dagenssamhalle.se%2Ffeed%2Fall
    private static final String TAG = "PostLiveData";
    public PostLiveData() {
        Tiny.fetch("https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fwww.dagenssamhalle.se%2Ffeed%2Fall", RSSResult.class).get(new TinyResult<RSSResult>() {

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
    }
}
