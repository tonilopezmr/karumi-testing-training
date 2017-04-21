package com.tonilopezmr.karumitestingtraining;

import android.content.SharedPreferences;

class SessionPreferences {

  private SharedPreferences sharedPreferences;

  public SessionPreferences(SharedPreferences sharedPreferences) {
    this.sharedPreferences = sharedPreferences;
  }

  public void setLoginSession(boolean session){
    SharedPreferences.Editor edit = sharedPreferences.edit();
    edit.putBoolean("login", session);
    edit.apply();
  }

  public boolean isUserLogin() {
    return sharedPreferences.getBoolean("login", false);
  }

}
