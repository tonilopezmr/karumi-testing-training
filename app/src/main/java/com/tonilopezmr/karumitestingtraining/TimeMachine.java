package com.tonilopezmr.karumitestingtraining;

import android.os.SystemClock;

class TimeMachine {
  public int getTimeInSeconds() {
    return (int) (SystemClock.elapsedRealtime() / 60);
  }
}
