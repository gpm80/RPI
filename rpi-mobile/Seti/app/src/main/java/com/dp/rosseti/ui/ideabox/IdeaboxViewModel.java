package com.dp.rosseti.ui.ideabox;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class IdeaboxViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public IdeaboxViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Idea Box fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}