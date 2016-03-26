package com.example.bryan.teamproject;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ListView;
import java.util.ArrayList;
import android.app.DialogFragment;
import android.app.FragmentTransaction;

import java.lang.reflect.Array;

public class DashboardActivity extends AppCompatActivity implements OnClickListener, OnTouchListener{

    private ImageButton project_add_btn;
    private ListView project_listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        project_add_btn = (ImageButton)findViewById(R.id.project_add_button);
        project_add_btn.setOnClickListener(this);

        project_listView = (ListView)findViewById(R.id.project_listView);
        refreshProjectList();
    }

    public void refreshProjectList()
    {
        Context context = getApplicationContext();
        ArrayList<Project> projects = new ArrayList<Project>();

        Project p1 = new Project(1, "Facebook2", "This ia gonna be fun.", "1", "0");
        Project p2 = new Project(2, "AlphaGo2", "This ia gonna rock.", "1", "0");

        projects.add(p1);
        projects.add(p2);

        ProjectListAdapter adapter = new ProjectListAdapter(context, projects);
        project_listView.setAdapter(adapter);
    }


    @Override
    public void onResume()
    {
        super.onResume();
        refreshProjectList();
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

    @TargetApi(11)
    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.project_add_button:
                this.project_add_btn.getBackground().setColorFilter(0x000, PorterDuff.Mode.SRC_ATOP);
                this.project_add_btn.invalidate();
                Log.i("ATTENTION", "We are here!!!!");

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                DialogFragment newFragment = new ProjectAddFragment();

                newFragment.show(ft, "add new project");
                break;
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event)
    {

        switch(v.getId())
        {
            case R.id.project_add_button:

                return true;

            default:
                return true;
        }
    }


}