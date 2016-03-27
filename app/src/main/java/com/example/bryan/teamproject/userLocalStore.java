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
        spEditor.putString("firstname", user.Firstname);
        spEditor.putString("lastname", user.Lastname);
        spEditor.putString("username", user.username);
        spEditor.putString("password", user.passWord);
        spEditor.putString("email", user.email);
        spEditor.putString("token", user.token);
        spEditor.commit();
    }

    public User getLoggedInUser() {
        String firstName = userLocalDatabase.getString("firstname", "");
        String lastName = userLocalDatabase.getString("lastname", "");
        String email = userLocalDatabase.getString("email", "");
        String username = userLocalDatabase.getString("username", "");
        String password = userLocalDatabase.getString("password","");
        String token = userLocalDatabase.getString("token", "");
        User storedUser = new User(username, password, token);
        return storedUser;
    }

    public User getNewUser(){
        String firstName = userLocalDatabase.getString("firstname", "");
        String lastName = userLocalDatabase.getString("lastname", "");
        String email = userLocalDatabase.getString("email", "");
        String username = userLocalDatabase.getString("username", "");
        String password = userLocalDatabase.getString("password","");
        User newUser = new User(firstName, lastName, email, username, password);
        return newUser;
    }

    public boolean getUserLoggedIn() {
        if (userLocalDatabase.getBoolean("loggedIn",false)==true) {
            return true;
        } else {
            return false;
        }
    }

    public static void setUserLoggedIn(boolean temp) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", temp);
        spEditor.commit();
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
            User user = new User(username, password, test.getTokenValue());
            storeUserData(user);
            setUserLoggedIn(true);
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
