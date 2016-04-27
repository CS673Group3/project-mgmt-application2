package com.example.bryan.teamproject;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Bryan on 4/27/16.
 */
@RunWith(AndroidJUnit4.class)
public class TokenRetrievalPassTests {
    @Rule
    public final ActivityTestRule<MainActivity> main = new ActivityTestRule<>(MainActivity.class);
    private String username, password, TeamName;
    private userLocalStore localStore;

    @Before
    public void setUp() {
        username = "brian";
        password = "brian";
        TeamName = "ProTeam";
        localStore = new userLocalStore(main.getActivity());
    }

    @Test
    public void testTokenRetrieval(){
        onView(withId(R.id.Login)).perform(ViewActions.click());
        onView(withId(R.id.username_input)).perform(ViewActions.typeText(username));
        onView(withId(R.id.password_input)).perform(ViewActions.typeText(password));
        onView(withId(R.id.submit)).perform(ViewActions.closeSoftKeyboard(),ViewActions.click());
        Log.i("Token retrieval", localStore.getLoggedInUser().token);
        Assert.assertNotNull("Token is retrieved ", localStore.getLoggedInUser().token.toString());
    }

}
