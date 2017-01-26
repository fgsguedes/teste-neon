package com.fgsguedes.testeneon.ui.activity;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.dannyroa.espresso_samples.recyclerview.RecyclerViewMatcher;
import com.fgsguedes.testeneon.App;
import com.fgsguedes.testeneon.R;
import com.fgsguedes.testeneon.di.ApplicationComponent;
import com.fgsguedes.testeneon.di.DaggerApplicationComponent;
import com.fgsguedes.testeneon.di.module.AndroidModule;
import com.fgsguedes.testeneon.di.module.MockedNetworkingModule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TransactionHistoryActivityTest {

  @Rule
  public ActivityTestRule<TransactionHistoryActivity> activityRule = new ActivityTestRule<>(TransactionHistoryActivity.class, true, false);

  MockWebServer server = new MockWebServer();

  @Before
  public void setUp() {
    ApplicationComponent applicationComponent = DaggerApplicationComponent.builder()
        .androidModule(new AndroidModule(InstrumentationRegistry.getTargetContext()))
        .networkingModule(new MockedNetworkingModule(server))
        .build();

    ((App) InstrumentationRegistry.getTargetContext().getApplicationContext())
        .setApplicationComponent(applicationComponent);
  }

  @Test
  public void testTransactionMapping() throws IOException, InterruptedException {

    InputStream stream = InstrumentationRegistry.getContext()
        .getAssets()
        .open("transactions_response.json");

    Source source = Okio.source(stream);
    BufferedSource buffer = Okio.buffer(source);

    String body = buffer.readUtf8();

    MockResponse mockResponse = new MockResponse()
        .setBody(body);

    server.start();
    server.enqueue(mockResponse);

    activityRule.launchActivity(new Intent());

    Locale locale = InstrumentationRegistry.getTargetContext()
        .getResources().getConfiguration().locale;

    onView(withRecyclerView(R.id.recycler_view_transactions).atPosition(0))
        .check(matches(hasDescendant(withText("Filipe Guedes 2"))))
        .check(matches(hasDescendant(withText(String.format(locale, "R$ %.2f", 320.0)))));

    onView(withRecyclerView(R.id.recycler_view_transactions).atPosition(1))
        .check(matches(hasDescendant(withText("Filipe Guedes 3"))))
        .check(matches(hasDescendant(withText(String.format(locale, "R$ %.2f", 75.0)))));

    onView(withRecyclerView(R.id.recycler_view_transactions).atPosition(2))
        .check(matches(hasDescendant(withText("Filipe Guedes 1"))))
        .check(matches(hasDescendant(withText(String.format(locale, "R$ %.2f", 22.0)))));

    server.shutdown();
  }

  private RecyclerViewMatcher withRecyclerView(@IdRes int viewId) {
    return new RecyclerViewMatcher(viewId);
  }
}
