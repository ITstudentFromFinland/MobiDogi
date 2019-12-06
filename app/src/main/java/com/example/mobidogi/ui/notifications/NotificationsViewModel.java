package com.example.mobidogi.ui.notifications;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class NotificationsViewModel extends ViewModel {

  private MutableLiveData<String> mText;

  public NotificationsViewModel() {
    mText = new MutableLiveData<>();
  }

  public LiveData<String> getText() {
    return mText;
  }
}
