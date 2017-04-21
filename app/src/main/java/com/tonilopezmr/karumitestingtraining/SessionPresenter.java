package com.tonilopezmr.karumitestingtraining;

public class SessionPresenter {

  interface View {
    void showLoader();

    void hideLoader();

    void enableLoginButton(boolean isEnable);

    void enableLogoutButton(boolean isEnable);

    void showForm();

    void hideForm();

    void showLoginError();

    void showLogoutError();
  }

  private SessionPreferences sessionPreferences;
  private SessionApiClient sessionApiClient;
  private View view;

  public SessionPresenter(SessionPreferences sessionPreferences, SessionApiClient sessionApiClient) {
    this.sessionPreferences = sessionPreferences;
    this.sessionApiClient = sessionApiClient;
  }

  public void setView(View view) {
    this.view = view;
  }

  public void init() {
    if (sessionPreferences.isUserLogin()) {
      userLogged();
    } else {
      userNotLogged();
    }
  }

  private void userLogged() {
    view.hideForm();
    view.hideLoader();
    view.enableLogoutButton(true);
    view.enableLoginButton(false);
    sessionPreferences.setLoginSession(true);
  }

  private void userNotLogged() {
    view.showForm();
    view.hideLoader();
    view.enableLoginButton(true);
    view.enableLogoutButton(false);
    sessionPreferences.setLoginSession(false);
  }

  public void login(String email, String name) {
    view.showLoader();
    view.enableLoginButton(false);
    sessionApiClient.login(email, name, new SessionApiClient.Callback() {
      @Override
      public void onSuccess() {
        userLogged();
      }

      @Override
      public void onError() {
        view.enableLoginButton(true);
        view.showLoginError();
        view.hideLoader();
      }
    });
  }

  public void logout() {
    view.showLoader();
    view.enableLogoutButton(false);
    sessionApiClient.logout(new SessionApiClient.Callback() {
      @Override
      public void onSuccess() {
        userNotLogged();
      }

      @Override
      public void onError() {
        view.enableLogoutButton(true);
        view.showLogoutError();
        view.hideLoader();
      }
    });
  }

}
