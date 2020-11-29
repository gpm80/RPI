package com.dp.rosseti.ui.ideabox;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dp.rosseti.data.db.entities.ShortIdea;
import com.dp.rosseti.data.db.entities.TopUser;
import com.dp.rosseti.data.repos.ShortIdeasRepository;
import com.dp.rosseti.data.repos.TopUsersRepository;

import java.util.List;

public class IdeaboxViewModel extends AndroidViewModel {

    private ShortIdeasRepository mRepository;
    private LiveData<List<ShortIdea>> mAllShortIdeas;

    public IdeaboxViewModel(Application application) {
        super(application);
        mRepository = new ShortIdeasRepository(application);
        mAllShortIdeas = mRepository.getAllShortIdeas();
    }

    LiveData<List<ShortIdea>> getAllShortIdeas() { return mAllShortIdeas; }

    public void insert(ShortIdea shortIdea) { mRepository.insert(shortIdea); }
}