package com.example.bryan.teamproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.view.Menu;

public class ProfileActivity extends AppCompatActivity implements OnClickListener{

    private TextView greeting_msg_txtView;
    private Button requirement_tracker_btn;
    private Button comm_tool;
    private Button issue_tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        greeting_msg_txtView = (TextView)findViewById(R.id.profile_greeting_msg_txtView);
        greeting_msg_txtView.setText("Welcome back, " + username);

        requirement_tracker_btn = (Button)findViewById(R.id.requirement_tracker_btn);
        comm_tool = (Button)findViewById(R.id.com_tool_btn);
        issue_tracker = (Button)findViewById(R.id.issue_tracker_btn);

        requirement_tracker_btn.setOnClickListener(this);
        comm_tool.setOnClickListener(this);
        issue_tracker.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){

        switch(v.getId()){

            case R.id.requirement_tracker_btn:

                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(intent);
                break;
            case R.id.com_tool_btn:
                break;
            case R.id.issue_tracker_btn:
                break;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.profile_info:
                return true;
            case R.id.change_password:
                return true;
            case R.id.log_out:
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
