package com.example.bryan.teamproject;

import android.app.Application;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ApplicationTestCase;
import android.util.Log;
import android.widget.TextView;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigInteger;
import java.security.SecureRandom;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private MainActivity mainActivity;
    private String username, password, TeamName;

    @Rule
    public final ActivityTestRule<MainActivity> main = new ActivityTestRule<>(MainActivity.class);
    public final ActivityTestRule<ProfileActivity> profile = new ActivityTestRule<>(ProfileActivity.class);


    @Before
    public void setUp() {
        username = "brian";
        password = "brian";
        TeamName = "ProTeam";
        mainActivity = main.getActivity();
    }

    @Test
    public void testProfileActivityPage() {
        onView(withId(R.id.Login)).perform(ViewActions.click());
        onView(withId(R.id.username_input)).perform(ViewActions.typeText(username));
        onView(withId(R.id.password_input)).perform(ViewActions.typeText(password));
        onView(withId(R.id.submit)).perform(ViewActions.closeSoftKeyboard(),ViewActions.click());
        onView(withId(R.id.requirement_tracker_btn)).perform(ViewActions.click());
        onView(withText(TeamName)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.project_listView)).perform(ViewActions.closeSoftKeyboard(),ViewActions.click());
    }

}