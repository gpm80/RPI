package com.dp.rosseti.ui.brainstorm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BrainstormViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BrainstormViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Brainstorm fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}