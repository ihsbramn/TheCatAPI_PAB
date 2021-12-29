package com.rab.thecatapi_pab.ui.breeds;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BreedsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BreedsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(" ");
    }

    public LiveData<String> getText() {
        return mText;
    }
}