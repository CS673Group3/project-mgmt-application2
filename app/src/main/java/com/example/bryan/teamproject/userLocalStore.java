package com.example.bryan.teamproject;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Bryan on 3/21/16.
 */
public class userLocalStore {
    public static final String SP_NAME = "userDetails";
    static SharedPreferences userLocalDatabase;

    public userLocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public static void storeUserData(User user) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("Firstname", user.Firstname);
        spEditor.putString("Lastname", user.Lastname);
        spEditor.putString("username", user.username);
        spEditor.putString("password", user.passWord);
        spEditor.putString("Email", user.email);
        spEditor.putString("Token", user.token);
        spEditor.commit();
    }

    public User getLoggedInUser() {
        String firstname = userLocalDatabase.getString("firstname", "");
        String lastname = userLocalDatabase.getString("lastname", "");
        String email = userLocalDatabase.getString("email", "");
        String username = userLocalDatabase.getString("username", "");
        String password = userLocalDatabase.getString("password", "");
        String token = userLocalDatabase.getString("token", "");
        User user = new User(firstname, lastname, email, username, password, token);
        return user;
    }

    public boolean getUserLoggedIn() {
        if (userLocalDatabase.getBoolean("LoggedIn", false) == true) {
            return true;
        } else {
            return false;
        }
    }

    public static void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    public static void setUserLoggedIn(String token) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("Token", token);
        spEditor.commit();
    }

    public String getUserLoggedInToken() {

            return userLocalDatabase.getString("token", "");
    }

    public void clearUserData() {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }

    public boolean authenticate(String username, String password){
        TokenTest test = new TokenTest(username, password);
        try {
            test.getToken();
            System.out.println("passed: " + test.getTokenValue());
            setUserLoggedIn(test.getTokenValue());
            //test.sendGet();
            return true;
        }

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
}
