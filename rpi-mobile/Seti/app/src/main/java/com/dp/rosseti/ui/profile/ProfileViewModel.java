package com.dp.rosseti.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<String> mName;
    private MutableLiveData<String> mPosition;
    private MutableLiveData<String> mExperience;
    private MutableLiveData<String> mRating;


    public ProfileViewModel() {
        mName = new MutableLiveData<>();
        mName.setValue("Alexander Smirnov");

        mPosition = new MutableLiveData<>();
        mPosition.setValue("Senior Engineer");

        mExperience = new MutableLiveData<>();
        mExperience.setValue("9 years in company");

        mRating = new MutableLiveData<>();
        mRating.setValue("Rating: 10500");
    }

    public LiveData<String> getText() {
        return mName;
    }
    public LiveData<String> getPosition() {
        return mPosition;
    }
    public LiveData<String> getExperience() {
        return mExperience;
    }
    public LiveData<String> getRating() {
        return mRating;
    }
}