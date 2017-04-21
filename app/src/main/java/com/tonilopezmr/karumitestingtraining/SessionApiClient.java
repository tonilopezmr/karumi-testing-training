package com.tonilopezmr.karumitestingtraining;

public class SessionApiClient {

  interface Callback {
    void onSuccess();

    void onError();
  }

  private ThreadExecutor threadExecutor;
  private TimeMachine timeMachine;

  public SessionApiClient(ThreadExecutor threadExecutor, TimeMachine timeMachine) {
    this.threadExecutor = threadExecutor;
    this.timeMachine = timeMachine;
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
        if (timeMachine.getTimeInSeconds() % 2 == 0) {
          callback.onSuccess();
        } else {
          callback.onError();
        }
      }
    });
  }

}
