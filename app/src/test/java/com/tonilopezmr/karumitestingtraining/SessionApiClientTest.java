package com.tonilopezmr.karumitestingtraining;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class SessionApiClientTest {

  @Test
  public void
  login_fails_if_the_user_is_empty() throws Exception {
    SessionApiClient sessionApiClient = new SessionApiClient(new FakeThreadExecutor());
    SessionApiClient.Callback mockCallback = mock(SessionApiClient.Callback.class);
    sessionApiClient.login("", "pass", mockCallback);


    verify(mockCallback).onError();
    verify(mockCallback, never()).onSuccess();
  }

}