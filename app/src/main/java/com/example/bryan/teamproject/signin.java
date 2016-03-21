package com.example.bryan.teamproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class signin extends AppCompatActivity {
    EditText userName, passWord;
    Button signIn;
    ImageButton backbutton;
    TextView forGot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        userName = (EditText) findViewById(R.id.username_input);
        passWord = (EditText) findViewById(R.id.password_input);
        signIn = (Button) findViewById(R.id.Submit);
        forGot = (TextView) findViewById(R.id.forgotPassword);
        backbutton = (ImageButton) findViewById(R.id.back_SignIn);

        View.OnClickListener handler = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.Submit:
                        v.setEnabled(false);
                        userName.setEnabled(false);
                        passWord.setEnabled(false);
                        String username = userName.getText().toString();
                        String password = passWord.getText().toString();
                        if (username.length() == 0) {
                            userName.requestFocus();
                            userName.setError("FIELD CANNOT BE EMPTY, PLEASE ENTER USERNAME");
                        } else if (!username.matches("[a-zA-Z]+")) {
                            userName.requestFocus();
                            userName.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                        } else if (password.length() == 0) {
                            passWord.requestFocus();
                            passWord.setError("FIELD CANNOT BE EMPTY, PLEASE ENTER CORRECT PASSWORD");
                        } else {
                            User user = new User(username, password, signin.this);
                            if (user.authenticate() == true) {
                                Toast.makeText(signin.this, "Validation Successful", Toast.LENGTH_LONG).show();
                                UserLogIn(user);
                            }
                        }
                        break;
                    case R.id.forgotPassword:
                        v.setEnabled(false);
                        // this to be filled in later
                        forgot();
                    case R.id.back_SignIn:
                        v.setEnabled(false);
                        //this to be filled in later
                        goback();
                }
            }
        };
    }

    private void UserLogIn(User returneduser) {
        userLocalStore.storeUserData(returneduser);
        userLocalStore.setUserLoggedIn(true);
        startActivity(new Intent(this, IceBox.class));
    }

    /**
     * method to navigate back to main activity page
     */
    private void goback() {

        startActivity(new Intent(this, MainActivity.class));
    }

    private void forgot() {
        Log.i("Verify ", " Authetication");
    }
}
