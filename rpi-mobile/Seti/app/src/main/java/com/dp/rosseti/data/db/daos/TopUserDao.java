package com.dp.rosseti.data.db.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.dp.rosseti.data.db.entities.TopUser;
import com.dp.rosseti.data.db.entities.User;

import java.util.List;

@Dao
public interface TopUserDao {

    // LiveData is a data holder class that can be observed within a given lifecycle.
    // Always holds/caches latest version of data. Notifies its active observers when the
    // data has changed. Since we are getting all the contents of the database,
    // we are notified whenever any of the database contents have changed.
    @Query("SELECT * from topusers_table ORDER BY chartline ASC")
    LiveData<List<TopUser>> getTopUsersByPosition();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TopUser topUser);

    @Query("DELETE FROM topusers_table")
    void deleteAll();
}
