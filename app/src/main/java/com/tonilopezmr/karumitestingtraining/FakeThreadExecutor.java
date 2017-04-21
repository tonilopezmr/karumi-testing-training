package com.tonilopezmr.karumitestingtraining;

class FakeThreadExecutor extends ThreadExecutor {
  @Override
  public void post(Runnable runnable) {
    runnable.run();
  }
}
