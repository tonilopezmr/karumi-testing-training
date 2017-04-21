package com.tonilopezmr.karumitestingtraining;

class ThreadExecutor {
  public void post(Runnable runnable) {
    new Thread(runnable).start();
  }
}
