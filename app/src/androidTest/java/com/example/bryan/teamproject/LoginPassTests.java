package com.example.bryan.teamproject;

import android.os.Bundle;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Bryan on 4/26/16.
 */

@RunWith(AndroidJUnit4.class)
public class LoginPassTests {
    @Rule
    public final ActivityTestRule<MainActivity> main = new ActivityTestRule<>(MainActivity.class);
    private String username, password, TeamName;

    @Before
    public void setUp() {
        username = "brian";
        password = "brian";
        TeamName = "ProTeam";
    }

    @Test
    public void testLogin() {
        onView(withText(TeamName)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.Login)).perform(ViewActions.click());
        onView(withId(R.id.username_input)).perform(ViewActions.typeText(username));
        onView(withId(R.id.password_input)).perform(ViewActions.typeText(password));
        onView(withId(R.id.submit)).perform(ViewActions.closeSoftKeyboard(), ViewActions.click());
        onView(withText(TeamName)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.profile_greeting_msg_txtView)).check(ViewAssertions.matches(withText("Welcome back," + "\t" +username +"\t")));
    }


}
