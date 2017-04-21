package com.tonilopezmr.karumitestingtraining;

import android.os.Handler;

class ThreadExecutor {
  public void post(final Runnable runnable) {
    new Handler().postDelayed(runnable, 2000);
  }
}
