package com.example.myguru.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.myguru.R;

import java.util.Objects;

public class Select_Location extends AppCompatActivity {

    /*...................................*/
    /*Declaring Variables*/
    private ImageView noida;
    private ImageView patna;
    private ImageView guwahati;
    private ImageView bengalore;
    private ImageView kolkata;
    /*End of declaring variables*/
    /*...................................*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_location);

        /*Method to initialise the Objects*/
        ObjectsInitialisations();
        /*End of initialisation*/

    }

    /*...............................................................................*/
    /*Method to move the  intent to given class*/
    public void moveIntentToDashboard() {
        Intent i = new Intent(this, Dashboard.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    /*End of this method*/
    /*...............................................................................*/



    /*..................................................................*/
    /*Putting all the Objects handling here*/
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.noida1:
                noida.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        moveIntentToDashboard();
                    }
                });
            case R.id.patna1:
                patna.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        moveIntentToDashboard();
                    }
                });
            case R.id.guwahati1:
                guwahati.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        moveIntentToDashboard();
                    }
                });
            case R.id.bengalore1:
                bengalore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        moveIntentToDashboard();
                    }
                });
            case R.id.kolkata1:
                kolkata.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        moveIntentToDashboard();
                    }
                });
        }
        return super.onOptionsItemSelected(item);
    }
    /*End of this method*/
    /*...................................................................*/


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    /*................................................*/
    /*Method of Objects  initialisations*/
    private void ObjectsInitialisations() {
        noida = findViewById(R.id.noida1);
        patna = findViewById(R.id.patna1);
        guwahati = findViewById(R.id.guwahati1);
        bengalore = findViewById(R.id.bengalore1);
        kolkata = findViewById(R.id.kolkata1);
    }
    /*End of this method*/
    /*................................................*/
}
