package se.my.daik.policheck.screen.main;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by daik on 2018-02-01.
 */

@Dao
public interface RssDao {

    @Insert
    void insert (RssEntry... entities);

    @Query("SELECT * FROM news_items")
    LiveData<List<RssEntry>> read();

    @Update
    void update(RssEntry... entities);

    @Delete
    void delete(RssEntry... entities);

}
