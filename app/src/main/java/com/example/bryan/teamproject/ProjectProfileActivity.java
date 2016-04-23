package com.example.bryan.teamproject;

import android.annotation.TargetApi;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import com.google.tabmanager.TabManager;

public class ProjectProfileActivity extends FragmentActivity {

    private TabHost tabHost;
    private TabManager tabManager;
    private String selectedProjectId;
    private String selectedProjectName;
    private String selectedProjectDescription;
    private boolean selectedProjectIsCompleted;


    @TargetApi(11)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_profile);

        //get tab manager
        tabHost = (TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();
        tabManager = new TabManager(this, tabHost, R.id.realtabcontent);


        Intent intent = getIntent();


        String[] tabNames = {"Info", "Icebox", "Iteration", "UserList"};
        for(String tabName : tabNames)
        {
            TabSpec tabSpec = tabHost.newTabSpec(tabName);
            tabSpec.setIndicator(tabName);
            tabManager.addTab(tabSpec, TabFragment.class, null);
        }

        if(savedInstanceState != null)
        {
            tabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
        }

        // this if block has to follow the previous savedInstanceState if block
        if(intent.getStringExtra("currentSelectedTabTag") != null)
        {
            Log.i("ATTENTIONS", intent.getStringExtra("currentSelectedTabTag"));
            tabHost.setCurrentTabByTag(intent.getStringExtra("currentSelectedTabTag"));
        }


        // change the color of the default action bar to our dark blue theme
        getActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>ProTeam </font>"));
    }

    @Override
    protected  void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putString("tab", tabHost.getCurrentTabTag());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
