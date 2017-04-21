package com.tonilopezmr.karumitestingtraining;

import android.os.SystemClock;

public class SessionApiClient {

  interface Callback {
    void onSuccess();

    void onError();
  }

  public void login(String email, String password, Callback callback) {

    if (email.equals("toni") && password.equals("the3rocks")) {
      callback.onSuccess();
    } else {
      callback.onError();
    }

  }

  public void logout(Callback callback) {
    if ((SystemClock.elapsedRealtime() / 60 ) % 2 == 0) {
      callback.onSuccess();
    } else {
      callback.onError();
    }
  }

}
