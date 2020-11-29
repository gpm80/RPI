package com.dp.rosseti.data.repos;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.dp.rosseti.data.db.RossetiDatabase;
import com.dp.rosseti.data.db.daos.TopUserDao;
import com.dp.rosseti.data.db.daos.UserDao;
import com.dp.rosseti.data.db.entities.TopUser;
import com.dp.rosseti.data.db.entities.User;

import java.util.List;

public class TopUsersRepository {

    private TopUserDao mTopUserDao;
    private LiveData<List<TopUser>> mAllTopUsers;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public TopUsersRepository(Application application) {
        RossetiDatabase db = RossetiDatabase.getDatabase(application);
        mTopUserDao = db.topUserDao();
        mAllTopUsers = mTopUserDao.getTopUsersByPosition();
        int i = 0;
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<TopUser>> getAllTopUsers() {
        return mAllTopUsers;
    }

    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    public void insert (TopUser topUser) {
        new insertAsyncTask(mTopUserDao).execute(topUser);
    }

    private static class insertAsyncTask extends AsyncTask<TopUser, Void, Void> {

        private TopUserDao mAsyncTaskDao;

        insertAsyncTask(TopUserDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TopUser... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}