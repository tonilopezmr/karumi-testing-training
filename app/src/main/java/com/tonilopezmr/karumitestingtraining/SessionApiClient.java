package com.tonilopezmr.karumitestingtraining;

import android.os.SystemClock;

public class SessionApiClient {

  interface Callback {
    void onSuccess();

    void onError();
  }

  private ThreadExecutor threadExecutor;

  public SessionApiClient(ThreadExecutor threadExecutor) {
    this.threadExecutor = threadExecutor;
  }

  public void login(final String email, final String password, final Callback callback) {
    threadExecutor.post(new Runnable() {
      @Override
      public void run() {
        if (email.equals("toni") && password.equals("the3rocks")) {
          callback.onSuccess();
        } else {
          callback.onError();
        }
      }
    });
  }

  public void logout(final Callback callback) {
    threadExecutor.post(new Runnable() {
      @Override
      public void run() {
        if ((SystemClock.elapsedRealtime() / 60 ) % 2 == 0) {
          callback.onSuccess();
        } else {
          callback.onError();
        }
      }
    });
  }

}
