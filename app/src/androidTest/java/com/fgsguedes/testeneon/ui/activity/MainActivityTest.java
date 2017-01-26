package com.fgsguedes.testeneon.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.fgsguedes.testeneon.data.repository.TokenRepository;
import com.fgsguedes.testeneon.di.ApplicationComponent;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends BaseActivityTest {

  @Rule
  public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class, true, false);

  private MockWebServer server = new MockWebServer();

  @Before
  public void setUp() throws IOException {
    super.setUp(server);
    server.start();
  }

  @After
  public void cleanup() throws IOException {
    server.shutdown();
  }

  @Test
  public void testGenerateTokenFromServer() throws IOException {
    clearSharedPreferences();

    ApplicationComponent applicationComponent = getApplicationComponent();
    TokenRepository tokenRepository = applicationComponent.getTokenRepository();

    server.enqueue(new MockResponse()
        .setBody(readFile("token_response.json")));

    activityRule.launchActivity(new Intent());

    verify(tokenRepository).generateToken();
    assertEquals(1, server.getRequestCount());
  }


  @Test
  public void testCachedTokenDontRequestNewOne() throws IOException {
    SharedPreferences preferences = getSharedPreferences();
    preferences.edit()
        .putString("token", "just-any-fake-token")
        .apply();

    ApplicationComponent applicationComponent = getApplicationComponent();
    TokenRepository tokenRepository = applicationComponent.getTokenRepository();

    activityRule.launchActivity(new Intent());

    verify(tokenRepository).generateToken();
    assertEquals(0, server.getRequestCount());
  }
}



