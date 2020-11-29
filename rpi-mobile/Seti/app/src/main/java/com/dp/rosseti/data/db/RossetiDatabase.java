package com.dp.rosseti.data.db;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.dp.rosseti.data.db.daos.ShortIdeaDao;
import com.dp.rosseti.data.db.daos.TopUserDao;
import com.dp.rosseti.data.db.daos.UserDao;
import com.dp.rosseti.data.db.entities.ShortIdea;
import com.dp.rosseti.data.db.entities.TopUser;
import com.dp.rosseti.data.db.entities.User;

import java.net.URI;

@Database(entities = {
                    User.class,
                    TopUser.class,
                    ShortIdea.class},
        version = 3,
        exportSchema = false)
public abstract class RossetiDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract TopUserDao topUserDao();
    public abstract ShortIdeaDao shortIdeaDao();

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
     * If you want to start with more test data, just add them.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final UserDao mDao;
        private final TopUserDao mTopUserDao;
        private final ShortIdeaDao mShortIdeaDao;

        String [] users = {"Alexander Smirnov", "Svetlana Lanoz", "Petr Gusarov", "Vladimir Putin", "Sergey Gorelik"};
        String [] positions = {"Senior SW Engineer", "Designer", "Backend Developer", "Backend Developer", "Project Manager"};
        String [] avatars = {"avatar", "logo", "logo", "logo", "logo"};

        int [] chartline = {1, 2, 3, 4, 5};
        String [] names = {"Alexander Smirnov", "Svetlana Lanoz", "Petr Gusarov", "Vladimir Putin", "Sergey Gorelik"};
        int [] rating = {10500, 9300, 6500, 5900, 1200};
        String [] topusers_avatars = {"avatar", "logo", "logo", "logo", "logo"};

        int [] short_idea_ids = {1, 2, 3, 4, 5};
        String [] short_idea_titles = {"Save planet", "Help the poor", "Clear rivers", "Build a power plant", "Save the children"};
        String [] short_idea_owner = {"Alexander Smirnov", "Svetlana Lanoz", "Petr Gusarov", "Alexander Smirnov", "Sergey Gorelik"};
        String [] short_idea_description = {"Really Good idea", "fsdifjasdjfjsdajfdsjafkljaskljfkldasjfkljsdllasdjfkladsjfkljasdklfjasdkljfklasdjfklsdfdfdf", "Really Good idea", "Really Good idea", "Really Good idea"};
        String [] short_idea_attachment = {"image.jpg", "image.jpg", "image.jpg", "image.jpg", "image.jpg"};
        String [] short_idea_status = {"Submitted", "Submitted", "Submitted", "Ready", "Under Analysis"};


        PopulateDbAsync(RossetiDatabase db) {
            mDao = db.userDao();
            mTopUserDao = db.topUserDao();
            mShortIdeaDao = db.shortIdeaDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            mDao.deleteAll();

            for( int i = 0; i <= users.length - 1; i++) {

                User user = new User(users[i], positions[i], "android.resource://com.dp.rosseti/drawable/" + avatars[i]);
                mDao.insert(user);
            }

            mTopUserDao.deleteAll();

            for( int i = 0; i <= chartline.length - 1; i++) {
                TopUser topUser = new TopUser(chartline[i], names[i], "android.resource://com.dp.rosseti/drawable/" + topusers_avatars[i], rating[i]);
                mTopUserDao.insert(topUser);
            }

            mShortIdeaDao.deleteAll();

            for( int i = 0; i <= short_idea_ids.length - 1; i++) {
                ShortIdea shortIdea = new ShortIdea(short_idea_ids[i], short_idea_titles[i], short_idea_owner[i], short_idea_description[i],
                        "android.resource://com.dp.rosseti/drawable/" + short_idea_attachment[i], short_idea_status[i]);
                mShortIdeaDao.insert(shortIdea);
            }

            return null;
        }
    }
}