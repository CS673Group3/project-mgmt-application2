package com.example.bryan.teamproject;

/**
 * Created by Bryan on 3/20/16.
 */

import android.content.Context;
import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;

import java.io.Console;

public class User {
    String Firstname, Lastname, username, passWord, email;
    Context context;


    /**
     * method to store data
     *
     * @param FirstName
     * @param LastName
     * @param Username
     * @param password
     */
    public User(String FirstName, String LastName, String Username, String password, String Email) {
        this.username = Username;
        this.Firstname = FirstName;
        this.Lastname = LastName;
        this.passWord = password;
        this.email = Email;
    }

    /**
     * method to cater to already existing user
     *
     * @param Username
     * @param Password
     */
    public User(String Username, String Password, Context context) {

        this.username = Username;
        this.passWord = Password;
        this.context = context;
    }

    /*public boolean authenticate() {
        RequestQueue queue;
        Cache cache = new DiskBasedCache(context.getCacheDir(), 1024 * 1024);
        Network network = new BasicNetwork(new HurlStack());
        queue = new RequestQueue(cache, network);
        queue.start();
        //url to get to
        String url = "http://168.122.15.84:8000/api-token-auth/username="+username+" password="+passWord+"";
        //String url = "http://128.197.103.77";
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Display the first 500 characters of the response string.
                        //   viewPanel.setText("Response is: "+ response.substring(0,500));
                        Log.d("", response.substring(0, 500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("That didn't work!", error.getMessage());
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
        return false;
    }*/

    public boolean authenticate(){
        TokenTest test = new TokenTest(username, passWord);

        try {
            test.getToken();
            System.out.println("passed: "+test.getTokenValue());
            test.sendGet();
           return true;
        }

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
}
