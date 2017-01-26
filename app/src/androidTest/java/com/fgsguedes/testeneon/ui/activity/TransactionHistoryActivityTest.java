package com.fgsguedes.testeneon.ui.activity;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.fgsguedes.testeneon.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Locale;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TransactionHistoryActivityTest extends BaseActivityTest {

  @Rule
  public ActivityTestRule<TransactionHistoryActivity> activityRule = new ActivityTestRule<>(TransactionHistoryActivity.class, true, false);

  private MockWebServer server = new MockWebServer();

  @Before
  public void setUp() {
    super.setUp(server);
  }

  @Test
  public void testTransactionMapping() throws IOException {

    MockResponse mockResponse = new MockResponse()
        .setBody(readFile("transactions_response.json"));

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
}
