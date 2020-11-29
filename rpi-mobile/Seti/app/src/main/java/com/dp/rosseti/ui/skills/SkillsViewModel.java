package com.dp.rosseti.ui.skills;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SkillsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SkillsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Skills fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}