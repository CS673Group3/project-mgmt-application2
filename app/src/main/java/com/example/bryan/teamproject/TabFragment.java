package com.example.bryan.teamproject;

/*
 * This is the java code for the fragment page users will see when they click different tabs at the top of the Project profile page created by ProjectProfileActivity
 *
 * Last Updated: 3/25/2016 By Raymond Wu
 */

import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TextView;
import android.util.Log;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.widget.AdapterView.OnItemClickListener;

import org.w3c.dom.Text;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import android.os.Handler;

/*
import data_objects.UserStory;
import util.ProjectManager;
import util.Utility;
import data_objects.Project;
*/

public class TabFragment extends Fragment implements OnClickListener, OnItemClickListener{

    private TextView tab_page_txtView;
    private String currentTabTag;
    private View view=null;
    private LayoutInflater inflater;
    private ViewGroup container;
    /* widgets for Project Info Fragment */
    private Button project_edit_btn;
    //
    /* Widget for Icebox Fragment */
    private ListView user_story_listView;
    private ImageButton user_story_add_btn;
    //

    /* Widget for Iterations Fragment */
    private ListView iterations_listView;
    private ImageButton iteration_add_btn;
    //

    /* Widget for UserList Fragment */
    private ListView userlist_listView;
    private ImageButton user_add_btn;
    private SimpleAdapter UserListViewAdapter;
    //

    /* This contains the data from the ProjectProfileActivity */
    private ProjectProfileActivity parentActivity;
    private String projectId;
    private String projectName;
    private String projectDescription;
    private Project selectedProject;
    /********/



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        this.inflater = inflater;
        this.container = container;

        parentActivity = (ProjectProfileActivity)getActivity();
        /*
        projectId = parentActivity.getProjectId();
        projectName = parentActivity.getSelectedProjectName();
        projectDescription = parentActivity.getSelectedProjectDescription();
        selectedProject = ProjectManager.findProjectByName(projectName);
        */

        TabHost tabHost = (TabHost)container.getParent().getParent();

        currentTabTag = tabHost.getCurrentTabTag();

        //String[] tabNames = Utility.getTabList();

        if(currentTabTag.equalsIgnoreCase("Info")) {
            view = this.inflater.inflate(R.layout.fragment_project_info, container, false);
            Log.i("INFO", "This is "+currentTabTag+" tab's content.");

            project_edit_btn = (Button)view.findViewById(R.id.project_edit_btn);
            project_edit_btn.setOnClickListener(this);

        }
        else if(currentTabTag.equalsIgnoreCase("Icebox"))
        {
            view = this.inflater.inflate(R.layout.fragment_icebox_info, container, false);
            int resource = R.layout.user_story_listview_item;
            String[] from = {"title", "description"};
            int[] to = {R.id.user_story_name, R.id.user_story_description};

            /* populating sample data */
            ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();

            /*
            for(UserStory userStory: selectedProject.getIcebox())
            {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("title", userStory.getTitle());
                map.put("description", userStory.getDescription());
                data.add(map);
            }
            */

            /*****/

            user_story_listView = (ListView)view.findViewById(R.id.user_story_listView);
            user_story_listView.setOnItemClickListener(this);
            Context context = getActivity().getApplicationContext();
            SimpleAdapter adapter = new SimpleAdapter(context, data, resource, from, to);
            user_story_listView.setAdapter(adapter);
            user_story_add_btn = (ImageButton)view.findViewById(R.id.user_story_add_button);

            user_story_add_btn.setOnClickListener(this);

        }
        else if(currentTabTag.equalsIgnoreCase("Iteration"))
        {
            view = this.inflater.inflate(R.layout.fragment_iterations_info, container, false);
            int resource = R.layout.iterations_listview_item;
            String[] from = {"title", "description"};
            int[] to = {R.id.iteration_name, R.id.iteration_description};

            /* populating sample data */
            ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();

            HashMap<String, String> map1 = new HashMap<String, String>();
            map1.put("title", "Iteration 1");
            map1.put("description", "This is a giant first step.");
            data.add(map1);

            HashMap<String, String> map2 = new HashMap<String, String>();
            map2.put("title", "Iteration 2");
            map2.put("description", "Wrap up the trivial things.");
            data.add(map2);

            /*****/

            iterations_listView = (ListView)view.findViewById(R.id.iterations_listView);
            iterations_listView.setOnItemClickListener(this);
            Context context = getActivity().getApplicationContext();
            SimpleAdapter adapter = new SimpleAdapter(context, data, resource, from, to);
            iterations_listView.setAdapter(adapter);

            iteration_add_btn = (ImageButton)view.findViewById(R.id.iteration_add_button);
            iteration_add_btn.setOnClickListener(this);

        }
        else if(currentTabTag.equalsIgnoreCase("UserList"))
        {
            view = this.inflater.inflate(R.layout.fragment_userlist_info, container, false);
            int resource = R.layout.userlist_listview_item;
            String[] from = {"username"};
            int[] to = {R.id.username};

            /* populating sample data */
            ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();

            HashMap<String, String> map1 = new HashMap<String, String>();
            map1.put("username", "Project Owners");
            data.add(map1);

            HashMap<String, String> map2 = new HashMap<String, String>();
            map2.put("username", "Steve");
            data.add(map2);

            HashMap<String, String> map3 = new HashMap<String, String>();
            map3.put("username", "Bill Gates");
            data.add(map3);


            HashMap<String, String> map4 = new HashMap<String, String>();
            map4.put("username", "Developers");
            data.add(map4);

            HashMap<String, String> map5 = new HashMap<String, String>();
            map5.put("username", "Mark");
            data.add(map5);

            HashMap<String, String> map6 = new HashMap<String, String>();
            map6.put("username", "Clients");
            data.add(map6);

            HashMap<String, String> map7 = new HashMap<String, String>();
            map7.put("username", "Jesse");
            data.add(map7);
            /*****/



            userlist_listView = (ListView)view.findViewById(R.id.userlist_listView);
            userlist_listView.setOnItemClickListener(this);
            Context context = getActivity().getApplicationContext();
            UserListViewAdapter = new SimpleAdapter(context, data, resource, from, to);
            userlist_listView.setAdapter(UserListViewAdapter);


            /* IMPORTANT - becasue the setAdapter() of the listview will cause a NullPointerException if we try to access its childs
             * using getChildAt() method right after the adapter is connected to the view. As a result, we delay the process of modifying
             * ths list's childs in order to avoid the nullPointerExcpeiton.
             * */
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {

                    LinearLayout wantedListItemView1 = (LinearLayout) userlist_listView.getChildAt(0);
                    LinearLayout wantedListItemView2 = (LinearLayout) userlist_listView.getChildAt(3);
                    LinearLayout wantedListItemView3 = (LinearLayout) userlist_listView.getChildAt(5);

                    if(wantedListItemView1 != null)
                    {
                        wantedListItemView1.setBackgroundResource(R.color.header_gray);
                        ((TextView)wantedListItemView1.getChildAt(0)).setTextColor(Color.WHITE);
                    }

                    if(wantedListItemView2 != null)
                    {
                        wantedListItemView2.setBackgroundResource(R.color.header_gray);
                        ((TextView)wantedListItemView2.getChildAt(0)).setTextColor(Color.WHITE);
                    }

                    if(wantedListItemView3 != null)
                    {
                        wantedListItemView3.setBackgroundResource(R.color.header_gray);
                        ((TextView)wantedListItemView3.getChildAt(0)).setTextColor(Color.WHITE);
                    }

                }
            }, 100);



            user_add_btn = (ImageButton)view.findViewById(R.id.user_add_button);
            user_add_btn.setOnClickListener(this);

        }

        Log.i("INFO", "TabFragment Called.");

        Log.i("IMPORTANT", "onCreateView() is called.");


        //tab_page_txtView = (TextView)view.findViewById(R.id.tab_page_msg);




        //tab_page_txtView.setText("Hey");

        refreshPage();

        return view;
    }


    public void refreshPage()
    {
        String[] tabNames = {"Info", "Icebox", "Iteration", "UserList"};

        if(currentTabTag.equalsIgnoreCase("Info"))
        {
            TextView projectNameTxtView = (TextView)view.findViewById(R.id.project_name_id);
            TextView projectDescriptionTxtView = (TextView)view.findViewById(R.id.project_description_id);

            projectNameTxtView.setText(projectName);
            projectDescriptionTxtView.setText(projectDescription);
        }
        else if(currentTabTag.equalsIgnoreCase("Icebox"))
        {
            Log.i("IMPORTANT", "onResume() is called.");
//            view = this.inflater.inflate(R.layout.fragment_icebox_info, container, false);
//            int resource = R.layout.user_story_listview_item;
//            String[] from = {"title", "description"};
//            int[] to = {R.id.user_story_name, R.id.user_story_description};
//
//            /* populating sample data */
//            ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
//
//            for(UserStory userStory: selectedProject.getIcebox())
//            {
//                HashMap<String, String> map = new HashMap<String, String>();
//                map.put("title", userStory.getTitle());
//                map.put("description", userStory.getDescription());
//                data.add(map);
//            }
//
//            /*****/
//            user_story_listView = (ListView)view.findViewById(R.id.user_story_listView);
//            Context context = getActivity().getApplicationContext();
//            SimpleAdapter adapter = new SimpleAdapter(context, data, resource, from, to);
//            user_story_listView.setAdapter(adapter);
//            user_story_add_btn = (ImageButton)view.findViewById(R.id.user_story_add_button);
//
//            user_story_add_btn.setOnClickListener(this);

        }


    }

    @Override
    public void onResume()
    {
        super.onResume();
        refreshPage();
    }


    @Override
    public void onClick(View v)
    {
        if(v.getId() == R.id.project_edit_btn)
        {
            Log.i("INFO", "onClick handler called.");


            // here we set the FragmentTransaction as a local variable to prevent "the same transaction is commited" error when users move back and forth between the two connected fragments
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(android.R.id.content, new ProjectEditFragment());
            transaction.addToBackStack(null); //add this fragment to the BackStack so when the back button is clicked in the next fragment, this one, which is right below it will be displayed instead.

            transaction.commit();
        }
        else if(v.getId() == R.id.user_story_add_button)
        {
            this.user_story_add_btn.getBackground().setColorFilter(0x000, PorterDuff.Mode.SRC_ATOP);
            this.user_story_add_btn.invalidate();



            android.app.FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
            DialogFragment newFragment = new UserStoryAddDialogFragment();

            newFragment.show(ft, "add new user story");
        }
        else if(v.getId() == R.id.iteration_add_button)
        {
            android.app.FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
            DialogFragment newFragment = new IterationAddDialogFragment();

            newFragment.show(ft, "add new iteration");
        }
        else if(v.getId() == R.id.user_add_button)
        {
            android.app.FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
            DialogFragment newFragment = new UserAddDialogFragment();

            newFragment.show(ft, "add new user");
        }

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id)
    {
        if(currentTabTag.equalsIgnoreCase("Icebox"))
        {
            UserStoryEditFragment userStoryEditFragment = new UserStoryEditFragment();
            Bundle args = new Bundle();
            args.putString("selectedUserStoryIndex", Integer.toString(position));
            userStoryEditFragment.setArguments(args);

            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(android.R.id.content, userStoryEditFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        else if(currentTabTag.equalsIgnoreCase("Iteration"))
        {

            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(android.R.id.content, new IterationEditFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        }
        else if(currentTabTag.equalsIgnoreCase("UserList"))
        {



            String selectedItemTxt = (String)((TextView)((LinearLayout)v).getChildAt(0)).getText();
            Log.i("IMPORTANT INFO 4", selectedItemTxt);
            if(!selectedItemTxt.equalsIgnoreCase("Project Owners") && !selectedItemTxt.equalsIgnoreCase("Developers") && !selectedItemTxt.equalsIgnoreCase("Clients"))
            {

                android.app.FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
                DialogFragment newFragment = new UserEditDialogFragment();

                newFragment.show(ft, "add new user");
            }

        }
    }

}
