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

  private SessionApiClient sessionApiClient;
  private View view;

  public SessionPresenter(SessionApiClient sessionApiClient) {
    this.sessionApiClient = sessionApiClient;
  }

  public void setView(View view){
    this.view = view;
  }

  public void login(String email, String name) {
    view.showLoader();
    view.enableLoginButton(false);
    sessionApiClient.login(email, name, new SessionApiClient.Callback() {
      @Override
      public void onSuccess() {
        view.hideForm();
        view.hideLoader();
        view.enableLogoutButton(true);
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
        view.showForm();
        view.hideLoader();
        view.enableLoginButton(true);
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
