package com.tonilopezmr.karumitestingtraining;

import android.os.Handler;
import android.os.SystemClock;

public class SessionApiClient {

  interface Callback {
    void onSuccess();

    void onError();
  }

  public void login(final String email, final String password, final Callback callback) {

    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        if (email.equals("toni") && password.equals("the3rocks")) {
          callback.onSuccess();
        } else {
          callback.onError();
        }
      }
    }, 2000);

  }

  public void logout(final Callback callback) {
    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        if ((SystemClock.elapsedRealtime() / 60 ) % 2 == 0) {
          callback.onSuccess();
        } else {
          callback.onError();
        }
      }
    }, 2000);
  }

}
