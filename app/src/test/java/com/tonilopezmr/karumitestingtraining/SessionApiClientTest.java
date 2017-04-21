package com.tonilopezmr.karumitestingtraining;

import org.junit.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class SessionApiClientTest {

  @Test
  public void
  login_fails_if_the_pass_is_empty() throws Exception {
    SessionApiClient.Callback mockCallback = mock(SessionApiClient.Callback.class);
    SessionApiClient sessionApiClient = new SessionApiClient(new FakeThreadExecutor(), new TimeMachine());
    sessionApiClient.login("user", "", mockCallback);


    verify(mockCallback).onError();
    verify(mockCallback, never()).onSuccess();
  }

  @Test
  public void
  login_fails_if_the_user_is_empty() throws Exception {
    SessionApiClient.Callback mockCallback = mock(SessionApiClient.Callback.class);
    SessionApiClient sessionApiClient = new SessionApiClient(new FakeThreadExecutor(), new TimeMachine());
    sessionApiClient.login("", "pass", mockCallback);


    verify(mockCallback).onError();
    verify(mockCallback, never()).onSuccess();
  }

  @Test
  public void
  login_fails_if_the_user_and_pass_not_correct() throws Exception {
    SessionApiClient.Callback mockCallback = mock(SessionApiClient.Callback.class);
    SessionApiClient sessionApiClient = new SessionApiClient(new FakeThreadExecutor(), new TimeMachine());
    sessionApiClient.login("user", "pass", mockCallback);


    verify(mockCallback).onError();
    verify(mockCallback, never()).onSuccess();
  }

  @Test
  public void
  login_fails_if_the_user_exists_but_pass_not_correct() throws Exception {
    SessionApiClient.Callback mockCallback = mock(SessionApiClient.Callback.class);
    SessionApiClient sessionApiClient = new SessionApiClient(new FakeThreadExecutor(), new TimeMachine());
    sessionApiClient.login("toni", "233", mockCallback);


    verify(mockCallback).onError();
    verify(mockCallback, never()).onSuccess();
  }

  @Test
  public void
  login_fails_if_the_pass_correct_but_user_not_correct() throws Exception {
    SessionApiClient.Callback mockCallback = mock(SessionApiClient.Callback.class);
    SessionApiClient sessionApiClient = new SessionApiClient(new FakeThreadExecutor(), new TimeMachine());
    sessionApiClient.login("user", "the3rocks", mockCallback);


    verify(mockCallback).onError();
    verify(mockCallback, never()).onSuccess();
  }

  @Test
  public void
  login_success_if_the_user_and_pass_correct() throws Exception {
    SessionApiClient.Callback mockCallback = mock(SessionApiClient.Callback.class);
    SessionApiClient sessionApiClient = new SessionApiClient(new FakeThreadExecutor(), new TimeMachine());
    sessionApiClient.login("toni", "the3rocks", mockCallback);


    verify(mockCallback).onSuccess();
    verify(mockCallback, never()).onError();
  }

  @Test
  public void
  returns_on_success_during_the_logout_if_the_milliseconds_is_even() throws Exception {
    SessionApiClient.Callback mockCallback = mock(SessionApiClient.Callback.class);
    TimeMachine stubEvenSeconds = mock(TimeMachine.class);
    given(stubEvenSeconds.getTimeInSeconds()).willReturn(2);
    SessionApiClient sessionApiClient = new SessionApiClient(new FakeThreadExecutor(), stubEvenSeconds);
    sessionApiClient.logout(mockCallback);


    verify(mockCallback).onSuccess();
    verify(mockCallback, never()).onError();
  }

  @Test
  public void
  returns_on_fails_during_the_logout_if_the_milliseconds_is_odd() throws Exception {
    SessionApiClient.Callback mockCallback = mock(SessionApiClient.Callback.class);
    TimeMachine stubEvenSeconds = mock(TimeMachine.class);
    given(stubEvenSeconds.getTimeInSeconds()).willReturn(3);
    SessionApiClient sessionApiClient = new SessionApiClient(new FakeThreadExecutor(), stubEvenSeconds);
    sessionApiClient.logout(mockCallback);


    verify(mockCallback).onError();
    verify(mockCallback, never()).onSuccess();
  }
}