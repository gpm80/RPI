package com.dp.rosseti.ui.challenge;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ChallengeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ChallengeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Challenges fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}