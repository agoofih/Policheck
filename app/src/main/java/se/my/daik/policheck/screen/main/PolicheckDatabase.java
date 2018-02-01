package se.my.daik.policheck.screen.main;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by daik on 2018-02-01.
 */

@Database(entities = {RssEntry.class}, version = 1, exportSchema = false)
public abstract class PolicheckDatabase extends RoomDatabase {

    public abstract RssDao getRssEntryDao();


}
