package se.my.daik.policheck;

import android.content.Context;

import se.my.daik.policheck.screen.main.RssEntryRepository;

/**
 * Created by daik on 2018-02-15.
 */

public class Factory {

    private static RssEntryRepository rssEntryRepository;

    public static RssEntryRepository getRssEntryRepository(Context context) {
        if (rssEntryRepository == null){
            rssEntryRepository = new RssEntryRepository(context);
        }

        return rssEntryRepository;
    }

}
