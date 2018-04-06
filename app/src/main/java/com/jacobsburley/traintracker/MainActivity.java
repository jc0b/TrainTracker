package com.jacobsburley.traintracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Console;

import static java.lang.System.out;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private String selectedOption;

    private String trainNumber;

    private EditText trainNumberField;

    private Spinner trainSelector;


    /*
        TODO:
            - variable renaming, I'm sure that its not super consistent in both the activity_main and this activity
            - break this activity down? or add a new activity for some new stuff (viewing trains etc)
            - figure out submission/storage (SQL?)
            - learn how to use fragments, a proper datepicker would be nice
            - make sure train option data is being selected and the selection value can be passed nicely to submission method or smth

     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trainNumberField = (EditText)findViewById(R.id.unitNumber);

        trainSelector = (Spinner) findViewById(R.id.train_selector);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.train_types, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        trainSelector.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        selectedOption = parent.getItemAtPosition(pos).toString();
        //debug code to see if we can actually get the value selected
        Log.i("info", selectedOption);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //can't really select nothing, presume this just means a default? we should pop-over and warn
        //update: this is never called? not sure what could happen to trigger this being called tbh
        Toast.makeText(this, "You can't submit for an undefined train type (yet)!", Toast.LENGTH_SHORT).show();
    }


    public void submitInformation(View view){
        trainNumber = trainNumberField.getText().toString();
        if(trainNumber.equals("")){
            Toast.makeText(this, "That's not a number!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, trainNumber, Toast.LENGTH_SHORT).show();
            //do submission
        }
        fieldCleanup();

    }

    public void fieldCleanup(){
        trainNumberField.clearComposingText(); //this doesn't do anything
        //not sure how to reset the trainSelector yet either hmm
    }

}
