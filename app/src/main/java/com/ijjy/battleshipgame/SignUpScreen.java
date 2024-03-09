package com.ijjy.battleshipgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignUpScreen extends AppCompatActivity {

    TextInputLayout username_var, email_var, password_var;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up_screen);

        username_var = findViewById(R.id.username_text_field_design);
        email_var = findViewById(R.id.email_text_field_design);
        password_var = findViewById(R.id.password_text_field_design);

    }

    public void loginbuttonclick(View view) {
        Intent intent = new Intent(getApplicationContext(),LoginScreen.class);
        startActivity(intent);
        finish();
    }

    public void signupbuttonclick(View view) {

        String username_ = username_var.getEditText().getText().toString();
        String email_ = email_var.getEditText().getText().toString();
        String password_ = password_var.getEditText().getText().toString();

        if(!username_.isEmpty()) {
            username_var.setError(null);
            username_var.setErrorEnabled(false);
            if(!email_.isEmpty()) {
                email_var.setError(null);
                email_var.setErrorEnabled(false);
                if(!password_.isEmpty()) {
                    password_var.setError(null);
                    password_var.setErrorEnabled(false);
                    //email regex for RFC-5322 Validation
                    if(email_.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")){
                        firebaseDatabase = FirebaseDatabase.getInstance();
                        reference = firebaseDatabase.getReference("datauser");

                        String username_s = username_var.getEditText().getText().toString().trim();
                        String email_s = email_var.getEditText().getText().toString().trim();
                        String password_s = password_var.getEditText().getText().toString();

                        //.trim() method (used above) removes trailing and leading whitespace

                        //Making sure username and email are unique (don't exist already)
                        //first: see if username is taken:
                        Query check_username = reference.orderByChild("username").equalTo(username_s);

                        check_username.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()){
                                    username_var.setError("Username already exists");

                                } else {
                                    username_var.setError(null);
                                    username_var.setErrorEnabled(false);

                                    //second: see if email is taken:
                                    Query check_email = reference.orderByChild("email").equalTo(email_s);
                                    check_email.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if(snapshot.exists()){
                                                email_var.setError("Email already taken");

                                            } else {
                                                email_var.setError(null);
                                                email_var.setErrorEnabled(false);

                                                StoringData storingData_s = new StoringData(username_s, email_s, password_s, 0);

                                                reference.child(username_s).setValue(storingData_s);

                                                StartScreen.userNameSS = username_s; //sending username information to StartScreen
                                                StatsScreen.scoreSS = 0; //setting new user's score information to StatsScreen

                                                Toast.makeText(getApplicationContext(), "Account created",Toast.LENGTH_SHORT).show();

                                                Intent intent = new Intent(getApplicationContext(), StartScreen.class);
                                                startActivity(intent);
                                                finish();

                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            //purposely nothing here
                                        }
                                    });
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                //purposely nothing here
                            }
                        });


                    }else {
                        email_var.setError("Invalid Email");
                    }
                }else {
                    password_var.setError("Please enter a password");
                }
            }else {
                email_var.setError("Please enter an email");
                if(password_.isEmpty()) password_var.setError("Please enter a password");
                else{
                    password_var.setError(null);
                    password_var.setErrorEnabled(false);
                }
            }
        }else {
            username_var.setError("Please enter a username");
            if(email_.isEmpty()) email_var.setError("Please enter an email");
            else{
                email_var.setError(null);
                email_var.setErrorEnabled(false);
            }
            if(password_.isEmpty()) password_var.setError("Please enter a password");
            else{
                password_var.setError(null);
                password_var.setErrorEnabled(false);
            }
        }

    }
}