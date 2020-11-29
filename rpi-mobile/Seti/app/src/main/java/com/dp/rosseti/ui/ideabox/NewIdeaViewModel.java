package com.dp.rosseti.ui.ideabox;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dp.rosseti.data.db.entities.ShortIdea;
import com.dp.rosseti.data.repos.ShortIdeasRepository;

import java.util.List;

public class NewIdeaViewModel extends AndroidViewModel {

    public NewIdeaViewModel(Application application) {
        super(application);
    }
}