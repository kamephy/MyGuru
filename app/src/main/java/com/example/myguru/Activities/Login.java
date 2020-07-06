package com.example.myguru.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.myguru.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    /*........................................*/
    /*Variables declaration*/
    private Button login_button;
    private Button register_button;
    Switch aSwitch;
    String flag;
    EditText username_login, password_login;
    DatabaseReference databaseReference;
    static String username, password;
    /*End of variables declaration*/
    /*........................................*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        /*Initialising the objects here*/
        initializeObjects();
        /*..................................*/

        /*If user has its login credentials then select login button*/
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredentials();
            }
        });
        /*........................................................*/



        /*If first user then select register button */
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToRegistrationActivity();
            }
        });
        /*..........................................*/


    }

    /*Method for selecting the location for teacher searching*/
    public void moveToLocationActivity() {
        Intent i = new Intent(this, Select_Location.class);
        startActivity(i);
    }
    /*End of this method*/


    /*Method for registration*/
    public void moveToRegistrationActivity() {
        Intent i = new Intent(this, Registration.class);
        startActivity(i);
    }
    /*End of this method*/


    /*Method of objects initialisation*/
    private void initializeObjects() {
        login_button = findViewById(R.id.login_button);
        register_button = findViewById(R.id.register_button);
        username_login = findViewById(R.id.Username_login);
        password_login = findViewById(R.id.password_login);
        aSwitch = findViewById(R.id.Login_switch);
    }
    /*End of this method*/


    /*.......................................................................................*/
    /*Validating data with database*/
    private void checkCredentials() {
        flag = "student";
        if (aSwitch.isChecked()) {
            flag = "teacher";
        } else {
            flag = "student";
        }
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(flag).child(username_login.getText().toString());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if ("username".equals(dataSnapshot.getKey())) {
                        username = dataSnapshot.getValue(String.class);
                    }
                    if ("password".equals(dataSnapshot.getKey())) {
                        password = dataSnapshot.getValue(String.class);
                        Toast.makeText(Login.this, password, Toast.LENGTH_SHORT).show();
                    }
                }

                if ((username.equals(username_login.getText().toString())) && (password.equals(password_login.getText().toString()))) {
                    Toast.makeText(Login.this, "Success", Toast.LENGTH_SHORT).show();
                    moveToLocationActivity();
                } else {
                    Toast.makeText(Login.this, "User not available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    /*End of this method for validation with database*/
    /*.......................................................................................*/
}

