package com.tonilopezmr.karumitestingtraining;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class SessionApiClientTest {

  private SessionApiClient sessionApiClient;

  @Before
  public void setUp() throws Exception {
    sessionApiClient = new SessionApiClient(new FakeThreadExecutor());
  }

  @Test
  public void
  login_fails_if_the_pass_is_empty() throws Exception {
    SessionApiClient.Callback mockCallback = mock(SessionApiClient.Callback.class);
    sessionApiClient.login("user", "", mockCallback);


    verify(mockCallback).onError();
    verify(mockCallback, never()).onSuccess();
  }

  @Test
  public void
  login_fails_if_the_user_is_empty() throws Exception {
    SessionApiClient.Callback mockCallback = mock(SessionApiClient.Callback.class);
    sessionApiClient.login("", "pass", mockCallback);


    verify(mockCallback).onError();
    verify(mockCallback, never()).onSuccess();
  }

  @Test
  public void
  login_fails_if_the_user_and_pass_not_correct() throws Exception {
    SessionApiClient.Callback mockCallback = mock(SessionApiClient.Callback.class);
    sessionApiClient.login("user", "pass", mockCallback);


    verify(mockCallback).onError();
    verify(mockCallback, never()).onSuccess();
  }

  @Test
  public void
  login_fails_if_the_user_exists_but_pass_not_correct() throws Exception {
    SessionApiClient.Callback mockCallback = mock(SessionApiClient.Callback.class);
    sessionApiClient.login("toni", "233", mockCallback);


    verify(mockCallback).onError();
    verify(mockCallback, never()).onSuccess();
  }

  @Test
  public void
  login_fails_if_the_pass_correct_but_user_not_correct() throws Exception {
    SessionApiClient.Callback mockCallback = mock(SessionApiClient.Callback.class);
    sessionApiClient.login("user", "the3rocks", mockCallback);


    verify(mockCallback).onError();
    verify(mockCallback, never()).onSuccess();
  }

  @Test
  public void
  login_success_if_the_user_and_pass_correct() throws Exception {
    SessionApiClient.Callback mockCallback = mock(SessionApiClient.Callback.class);
    sessionApiClient.login("toni", "the3rocks", mockCallback);


    verify(mockCallback).onSuccess();
    verify(mockCallback, never()).onError();
  }

}