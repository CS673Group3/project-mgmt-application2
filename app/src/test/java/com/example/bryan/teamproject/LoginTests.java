package com.example.bryan.teamproject;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ApplicationTestCase;
import android.widget.Button;
import android.widget.EditText;

import com.example.bryan.teamproject.User;
import com.example.bryan.teamproject.signin;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Matchers;
import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class LoginTests{

    @Mock
   public signin login;
   public Register register;
   public User user;
    public userLocalStore localStore;
    public String users, pass;

    @Before
    public void setUp() throws Exception{
        login = mock(signin.class);
        register = mock(Register.class);
        user = mock(User.class);
       localStore = mock(userLocalStore.class);
        users = "josh";
        pass = "josh";
        user = new User(users, pass);
    }

    @Test
    public void testUsers(){
       assertSame(user.username, users);
        assertSame(user.passWord, pass);
    }
}