package com.example.myguru.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.myguru.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {

    /*...............................*/
    /*Variables declaration*/
    EditText username, email, password, confirm_password;
    DatabaseReference userReference;
    Button submit_registration;
    String unique_key;
    RadioGroup radioGroup;
    RadioButton button, button2;
    String character_type;
    View parentLayout;
    ProgressDialog progressDialog;
    /*...............................*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);

        initialise_objects();

        getSelectionId();

        submit_data();

    }

    /*......................................*/
    /*Method for initialise the objects*/
    public void initialise_objects() {
        username = findViewById(R.id.user_name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
        submit_registration = findViewById(R.id.register_button);
        userReference = FirebaseDatabase.getInstance().getReference("Users");
        unique_key = userReference.push().getKey();
        button = findViewById(R.id.radio_stu);
        button2 = findViewById(R.id.radio_tea);
        radioGroup = findViewById(R.id.toggle);
        parentLayout = findViewById(android.R.id.content);
        progressDialog = new ProgressDialog(this);
    }
    /*End of this method*/
    /*......................................*/


    /*.................................................................................*/
    /*Get the selection whether teacher or student using radio group*/
    public void getSelectionId() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_stu:
                        character_type = "student";
                        button.setTextColor(Color.parseColor("#ffffff"));
                        button2.setTextColor(Color.parseColor("#000000"));
                        break;
                    case R.id.radio_tea:
                        character_type = "teacher";
                        button2.setTextColor(Color.parseColor("#ffffff"));
                        button.setTextColor(Color.parseColor("#000000"));
                }
            }
        });
    }
    /*End of this method*/
    /*................................................................................*/


    /*............................................................................*/
    /*Method for pushing the back end data to firebase*/
    public void submit_data() {
        submit_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.setMessage("Please wait..!!");
                progressDialog.setCancelable(false);
                progressDialog.show();

                if (radioGroup.getCheckedRadioButtonId() == R.id.radio_stu) {
                    character_type = "student";
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.radio_tea) {
                    character_type = "teacher";
                }

                if (password.getText().toString().equals(confirm_password.getText().toString())) {
                    userReference.child(character_type).child(username.getText().toString()).child("username").setValue(username.getText().toString());
                    userReference.child(character_type).child(username.getText().toString()).child("email").setValue(email.getText().toString());
                    userReference.child(character_type).child(username.getText().toString()).child("password").setValue(password.getText().toString());
                    userReference.child(character_type).child(username.getText().toString()).child("user_type").setValue(character_type);
                    progressDialog.dismiss();
                    Snackbar.make(parentLayout, "Successfully registered..!!", Snackbar.LENGTH_LONG).setDuration(2000).show();
                    finish();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(Registration.this, "Passwords not matching..!!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    /*End of pushing data to firebase*/
    /*............................................................................................*/
}
