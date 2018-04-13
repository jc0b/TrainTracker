package com.jacobsburley.traintracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String selectedOption;

    private String trainNumber;

    private String date;

    private EditText trainNumberField,
                    dateField;

    public ArrayList<Train> trainCollection = new ArrayList<>();


    /*
        TODO:
            - variable renaming, I'm sure that its not super consistent in both the activity_main.xml and this activity
            - break this activity down? or add a new activity for some new stuff (viewing trains etc)
            - figure out submission/storage (SQL?)
                - https://developer.android.com/training/data-storage/files.html#java
            - learn how to use fragments, a proper datepicker would be nice
            - make sure train option data is being selected and the selection value can be passed nicely to submission method or smth
            - remove type selection, but add an activity to view stored data and edit it (in case our inference is wrong)
            - add lifecycle stuff
            - transitions aren't great, use a LOT of gpu memory and are laggy. Not sure how to fix this yet
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitInformation(view);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        trainNumberField = (EditText)findViewById(R.id.unitNumber);

        dateField = (EditText) findViewById(R.id.ridden_date_input);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onPause(){
        super.onPause();
        //TODO: store data to file when another activity runs (we only have a single activity for now but we will need one for displaying saved data probably)
    }

    @Override
    protected void onResume(){
        super.onResume();
        //TODO: load data from file when we switch back to this activity

    }

    @Override
    protected void onStop(){
        super.onStop();
        //TODO: store data to file when user switches to another app
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        //TODO: load data from file when user switches back to this app
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // Handle Navigation Options
        Intent intent;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        switch (id) {
            case R.id.nav_add:
                //do nothing, we are already here!!
                break;
            case R.id.nav_about:
                intent = new Intent(this, AboutActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                this.startActivity(intent);
                drawer.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_collection:
                intent = new Intent(this, CollectionActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                this.startActivity(intent);
                drawer.closeDrawer(GravityCompat.START);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void submitInformation(View view){
        trainNumber = trainNumberField.getText().toString();
        date = dateField.getText().toString();
        int trainNumberInt = (int) Long.parseLong(trainNumber, 10);
        if(trainNumber.equals("") | date.equals("")){
            Snackbar.make(view, "Either your date or set number is invalid!", Snackbar.LENGTH_LONG).show();
        }else{
            Snackbar.make(view, trainNumber+" "+date, Snackbar.LENGTH_LONG).show();
            trainCollection.add(new Train(trainNumberInt, date));
            //do submission
        }
        fieldCleanup();

    }

    public void fieldCleanup(){
        trainNumberField.clearComposingText(); //this doesn't do anything
        //not sure how to reset the trainSelector yet either hmm
    }
}
