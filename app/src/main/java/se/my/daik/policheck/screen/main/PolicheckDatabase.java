package se.my.daik.policheck.screen.main;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by daik on 2018-02-01.
 */

@Database(entities = {RssEntry.class}, version = 1, exportSchema = false)
public abstract class PolicheckDatabase extends RoomDatabase {

    private static PolicheckDatabase database;

    public abstract RssDao getRssEntryDao();


    public static PolicheckDatabase getInstance(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context, PolicheckDatabase.class, "poli-check-database.db").build();
        }

        return database;
    }

}
