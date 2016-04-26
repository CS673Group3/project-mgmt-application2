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
    private SecureRandom random;
    private MainActivity mainActivity;
    private ProfileActivity profileActivity;
    private Register registerActivity;
    private String username, password, TeamName;
    private User user;
    private userLocalStore localStore;
    @Rule
    public final ActivityTestRule<MainActivity> main = new ActivityTestRule<>(MainActivity.class);
    public final ActivityTestRule<ProfileActivity> profile = new ActivityTestRule<>(ProfileActivity.class);
    public final ActivityTestRule<Register> register = new ActivityTestRule<>(Register.class);


    @Before
    public void setUp() {
        username = "josh";
        password = "josh";
        TeamName = "ProTeam";
        profileActivity = profile.getActivity();
        mainActivity = main.getActivity();
        registerActivity = register.getActivity();
        localStore = new userLocalStore(mainActivity);

    }

    @Test
    public void testLogin() {
        onView(withText(TeamName)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.Login)).perform(ViewActions.longClick());
        onView(withId(R.id.username_input)).perform(ViewActions.typeText(username));
        onView(withId(R.id.password_input)).perform(ViewActions.typeText(password));
        onView(withId(R.id.submit)).perform(ViewActions.longClick());
        onView(withText(TeamName)).check(ViewAssertions.matches(isDisplayed()));
        //onView(withText(username)).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void testProfileActivityPage() {
        onView(withId(R.id.Login)).perform(ViewActions.longClick());
        onView(withId(R.id.username_input)).perform(ViewActions.typeText(username));
        onView(withId(R.id.password_input)).perform(ViewActions.typeText(password));
        onView(withId(R.id.submit)).perform(ViewActions.longClick());
        onView(withId(R.id.requirement_tracker_btn)).perform(ViewActions.click());
        onView(withText(TeamName)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.project_listView)).perform(ViewActions.longClick());
    }

    @Test
    public void testTokenRetrieval(){
        onView(withId(R.id.Login)).perform(ViewActions.click());
        onView(withId(R.id.username_input)).perform(ViewActions.typeText(username));
        onView(withId(R.id.password_input)).perform(ViewActions.typeText(password));
        onView(withId(R.id.submit)).perform(ViewActions.longClick());
        Log.i("Token retrieval",localStore.getLoggedInUser().token);
        Assert.assertNotNull("Token is retrieved ", localStore.getLoggedInUser().token.toString());
    }

    @Test
    public void testFailRegister() {
        String newUSer, newPass, newFirstname, newLastname, newEmail;
        newUSer = failGenerate();
        newPass = failGenerate();
        newFirstname =  failGenerate();
        newLastname = failGenerate();
         newEmail = failGenerate()+"@gmail.com";
        onView(withText(TeamName)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.SignUp)).perform(ViewActions.longClick());
        onView(withId(R.id.username_input)).perform(ViewActions.typeText(newUSer));
        onView(withId(R.id.password_input)).perform(ViewActions.typeText(newPass));
        onView(withId(R.id.confirmPassword_input)).perform(ViewActions.typeText(newPass));
        onView(withId(R.id.firstname_Input)).perform(ViewActions.typeText(newFirstname));
        onView(withId(R.id.lastname_input)).perform(ViewActions.typeText(newLastname));
        onView(withId(R.id.Email_input)).perform(ViewActions.typeText(newEmail));
        onView(withId(R.id.SignUpbtn)).perform(ViewActions.longClick());

    }
    // generate string
    public  String failGenerate(){
            random = new SecureRandom();
            return new BigInteger(130, random).toString(12);
    }

    // generate string
    public  String passGenerate(){
        random = new SecureRandom();
        return new BigInteger(24, random).toString(12);
    }

    @Test
    public void testPassRegister() {
        String newUSer, newPass, newFirstname, newLastname, newEmail;
        newUSer =  passGenerate();
        newPass = passGenerate();
        newFirstname =  passGenerate();
        newLastname = passGenerate();
        newEmail = passGenerate()+"@gmail.com";
        onView(withText(TeamName)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.SignUp)).perform(ViewActions.longClick());
        onView(withId(R.id.username_input)).perform(ViewActions.typeText(newUSer));
        onView(withId(R.id.password_input)).perform(ViewActions.typeText(newPass));
        onView(withId(R.id.confirmPassword_input)).perform(ViewActions.typeText(newPass));
        onView(withId(R.id.firstname_Input)).perform(ViewActions.typeText(newFirstname));
        onView(withId(R.id.lastname_input)).perform(ViewActions.typeText(newLastname));
        onView(withId(R.id.Email_input)).perform(ViewActions.typeText(newEmail));
        onView(withId(R.id.SignUpbtn)).perform(ViewActions.longClick());

    }
}