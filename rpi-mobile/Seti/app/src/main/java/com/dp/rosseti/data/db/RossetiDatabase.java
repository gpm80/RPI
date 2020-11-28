package com.dp.rosseti.data.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.dp.rosseti.data.db.daos.UserDao;
import com.dp.rosseti.data.db.entities.User;

@Database(entities = {User.class}, version = 1,  exportSchema = false)
public abstract class RossetiDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    private static RossetiDatabase INSTANCE;

    public static RossetiDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RossetiDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RossetiDatabase.class, "rosseti_database")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            // Migration is not part of this codelab.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    /**
     * Populate the database in the background.
     * If you want to start with more words, just add them.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final UserDao mDao;
        String [] users = {"Alexander Smirnov", "Svetlana Lanoz", "Petr"};

        PopulateDbAsync(RossetiDatabase db) {
            mDao = db.userDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            mDao.deleteAll();

            for( int i = 0; i <= users.length - 1; i++) {
                User user = new User(users[i]);
                mDao.insert(user);
            }
            return null;
        }
    }
}