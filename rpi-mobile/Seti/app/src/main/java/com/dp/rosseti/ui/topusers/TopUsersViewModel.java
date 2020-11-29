package com.dp.rosseti.ui.topusers;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dp.rosseti.data.db.entities.TopUser;
import com.dp.rosseti.data.db.entities.User;
import com.dp.rosseti.data.repos.TopUsersRepository;
import com.dp.rosseti.data.repos.UsersRepository;

import java.util.List;

public class TopUsersViewModel extends AndroidViewModel {

    private TopUsersRepository mRepository;
    private LiveData<List<TopUser>> mAllTopUsers;

    public TopUsersViewModel (Application application) {
        super(application);
        mRepository = new TopUsersRepository(application);
        mAllTopUsers = mRepository.getAllTopUsers();
    }

    LiveData<List<TopUser>> getAllTopUsers() { return mAllTopUsers; }

    public void insert(TopUser topUser) { mRepository.insert(topUser); }
}