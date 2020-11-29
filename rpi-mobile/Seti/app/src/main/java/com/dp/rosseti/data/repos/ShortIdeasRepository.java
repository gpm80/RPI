package com.dp.rosseti.data.repos;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.dp.rosseti.data.db.RossetiDatabase;
import com.dp.rosseti.data.db.daos.ShortIdeaDao;
import com.dp.rosseti.data.db.daos.UserDao;
import com.dp.rosseti.data.db.entities.ShortIdea;
import com.dp.rosseti.data.db.entities.User;

import java.util.List;

public class ShortIdeasRepository {

    private ShortIdeaDao mShortIdeaDao;
    private LiveData<List<ShortIdea>> mAllShortIdeas;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public ShortIdeasRepository(Application application) {
        RossetiDatabase db = RossetiDatabase.getDatabase(application);
        mShortIdeaDao = db.shortIdeaDao();
        mAllShortIdeas = mShortIdeaDao.getAllShortIdeas();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<ShortIdea>> getAllShortIdeas() {
        return mAllShortIdeas;
    }

    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    public void insert (ShortIdea shortIdea) {
        new insertAsyncTask(mShortIdeaDao).execute(shortIdea);
    }

    private static class insertAsyncTask extends AsyncTask<ShortIdea, Void, Void> {

        private ShortIdeaDao mAsyncTaskDao;

        insertAsyncTask(ShortIdeaDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ShortIdea... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}