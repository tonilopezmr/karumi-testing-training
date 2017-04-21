package com.tonilopezmr.karumitestingtraining;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SessionPresenterTest {

  @Mock
  private SessionApiClient apiClient;

  @Mock
  private SessionPresenter.View view;

  @Mock
  private SessionPreferences preferences;

  @Test
  public void
  show_error_when_user_and_pass_is_empty() throws Exception {
    SessionPresenter presenter = new SessionPresenter(preferences, new ErrorLoginSession(null, null));
    presenter.setView(view);

    presenter.login("", "");

    verify(view).showLoginError();
    verify(view).hideLoader();
    verify(view).enableLoginButton(false);
  }

  @Test
  public void
  show_error_when_user_is_empty() throws Exception {
    SessionPresenter presenter = new SessionPresenter(preferences, new ErrorLoginSession(null, null));
    presenter.setView(view);

    presenter.login("", "the3rocks");

    verify(view).showLoginError();
    verify(view).hideLoader();
    verify(view).enableLoginButton(false);
  }

  @Test
  public void
  show_success_when_user_and_pass_correct() throws Exception {
    SessionPresenter presenter = new SessionPresenter(preferences, new SuccessLoginSession(null, null));
    presenter.setView(view);

    presenter.login("toni", "the3rocks");

    verify(view).hideForm();
    verify(view).hideLoader();
    verify(view).enableLogoutButton(true);
    verify(view, times(2)).enableLoginButton(false);
  }

  @Test
  public void
  when_init_ui_and_user_is_logged_login_disabled() throws Exception {
    given(preferences.isUserLogged()).willReturn(true);

    SessionPresenter presenter = new SessionPresenter(preferences, apiClient);
    presenter.setView(view);
    presenter.init();

    verify(view).hideForm();
    verify(view).hideLoader();
    verify(view).enableLogoutButton(true);
    verify(view).enableLoginButton(false);
  }

  @Test
  public void
  when_init_ui_and_user_is_not_logged_logout_disabled() throws Exception {
    given(preferences.isUserLogged()).willReturn(false);

    SessionPresenter presenter = new SessionPresenter(preferences, apiClient);
    presenter.setView(view);
    presenter.init();

    verify(view).showForm();
    verify(view).hideLoader();
    verify(view).enableLogoutButton(false);
    verify(view).enableLoginButton(true);
  }

  @Test
  public void
  when_click_login_show_loader_and_disable_login() throws Exception {
    SessionPresenter presenter = new SessionPresenter(preferences, apiClient);
    presenter.setView(view);

    presenter.login("", "");

    verify(view).showLoader();
    verify(view).enableLoginButton(false);
    verify(view, never()).hideLoader();
  }


  private class SuccessLoginSession extends SessionApiClient {

    public SuccessLoginSession(ThreadExecutor threadExecutor, TimeMachine timeMachine) {
      super(threadExecutor, timeMachine);
    }

    @Override
    public void login(String email, String password, Callback callback) {
      callback.onSuccess();
    }
  }

  private class ErrorLoginSession extends SessionApiClient {

    public ErrorLoginSession(ThreadExecutor threadExecutor, TimeMachine timeMachine) {
      super(threadExecutor, timeMachine);
    }

    @Override
    public void login(String email, String password, Callback callback) {
      callback.onError();
    }
  }

}