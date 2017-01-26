package com.fgsguedes.testeneon.ui.activity;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.fgsguedes.testeneon.App;
import com.fgsguedes.testeneon.R;
import com.fgsguedes.testeneon.data.repository.TransactionsRepository;
import com.fgsguedes.testeneon.di.ApplicationComponent;
import com.fgsguedes.testeneon.matchers.ToastMatcher;
import com.fgsguedes.testeneon.model.Contact;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SendMoneyActivityTest extends BaseActivityTest {

  @Rule
  public ActivityTestRule<SendMoneyActivity> activityRule = new ActivityTestRule<>(SendMoneyActivity.class, true, false);

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
  public void testAmountNotSubmittedOnInvalidValue() throws IOException {

    activityRule.launchActivity(new Intent());

    onView(withRecyclerView(R.id.recycler_view_contacts).atPosition(0))
        .perform(click());

    onView(withId(R.id.button_send_money))
        .perform(click());

    onView(withText(R.string.invalid_transaction_value))
        .inRoot(new ToastMatcher())
        .check(matches(isDisplayed()));

    onView(withId(R.id.edit_text_transaction_value))
        .perform(typeText("0"));

    onView(withId(R.id.button_send_money))
        .perform(click());

    onView(withText(R.string.invalid_transaction_value))
        .inRoot(new ToastMatcher())
        .check(matches(isDisplayed()));

    assertEquals(0, server.getRequestCount());
  }

  @Test
  public void testMoneySubmission() throws IOException, InterruptedException {
    server.enqueue(new MockResponse());

    ApplicationComponent applicationComponent = ((App) InstrumentationRegistry.getTargetContext()
        .getApplicationContext())
        .getApplicationComponent();

    TransactionsRepository transactionsRepository = applicationComponent.getTransactionsRepository();

    activityRule.launchActivity(new Intent());

    onView(withRecyclerView(R.id.recycler_view_contacts).atPosition(0))
        .perform(click());

    onView(withId(R.id.edit_text_transaction_value))
        .perform(typeText("50"));

    onView(withId(R.id.button_send_money))
        .perform(click());

    verify(transactionsRepository).sendMoney(new Contact(1, "Any", "", ""), 50);
    assertEquals(1, server.getRequestCount());
  }

}



