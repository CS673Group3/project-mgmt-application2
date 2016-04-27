package com.example.bryan.teamproject;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigInteger;
import java.security.SecureRandom;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Bryan on 4/27/16.
 */
@RunWith(AndroidJUnit4.class)
public class RegisterFailTests {
    private SecureRandom random;
    private String TeamName;
    @Rule
    public final ActivityTestRule<MainActivity> main = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        TeamName = "ProTeam";
    }
    @Test
    public void testFailRegister() {
        String newUSer, newPass, newFirstname, newLastname, newEmail;
        newUSer = failGenerate();
        newPass = failGenerate()+failGenerate();
        newFirstname =  failGenerate();
        newLastname = failGenerate();
        newEmail = failGenerate()+"@gmail.com";
        onView(withText(TeamName)).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.username_input)).perform(ViewActions.typeText(newUSer));
        onView(withId(R.id.password_input)).perform(ViewActions.typeText(newPass));
        onView(withId(R.id.confirmPassword_input)).perform(ViewActions.typeText(newPass));
        onView(withId(R.id.firstname_Input)).perform(ViewActions.typeText(newFirstname));
        onView(withId(R.id.lastname_input)).perform(ViewActions.typeText(newLastname));
        onView(withId(R.id.Email_input)).perform(ViewActions.typeText(newEmail));
        onView(withId(R.id.Register)).perform(scrollTo(), click());

    }
    // generate string
    public  String failGenerate(){
        random = new SecureRandom();
        return new BigInteger(13, random).toString(12);
    }

}
