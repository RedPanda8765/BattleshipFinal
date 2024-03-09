package com.ijjy.battleshipgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginScreen extends AppCompatActivity {

    Button signupbutton, loginbutton;
    TextInputLayout username_var, password_var;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_screen);

        signupbutton = findViewById(R.id.button_signup);
        loginbutton = findViewById(R.id.button_done); //corresponds with "Done" button
        username_var = findViewById(R.id.username_text_field_design);
        password_var = findViewById(R.id.password_text_field_design);


        loginbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String username_ = username_var.getEditText().getText().toString();
                String password_ = password_var.getEditText().getText().toString();

                if(!username_.isEmpty()) {
                    username_var.setError(null);
                    username_var.setErrorEnabled(false);
                    if(!password_.isEmpty()) {
                        password_var.setError(null);
                        password_var.setErrorEnabled(false);

                        final String username_data = username_var.getEditText().getText().toString();
                        final String password_data = password_var.getEditText().getText().toString();

                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference reference = firebaseDatabase.getReference("datauser");

                        //first seeing if the username exists:
                        Query check_username = reference.orderByChild("username").equalTo(username_data);

                        check_username.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()){
                                    username_var.setError(null);
                                    username_var.setErrorEnabled(false);
                                    String passwordcheck = snapshot.child(username_data).child("password").getValue(String.class);
                                    int score = snapshot.child(username_data).child("score").getValue(Integer.class).intValue();

                                    //seeing if the password inputted matches the one in the database:
                                    if(passwordcheck.equals(password_data)){
                                        password_var.setError(null);
                                        password_var.setErrorEnabled(false);
                                        Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();

                                        StartScreen.userNameSS = username_data; //sending username information to StartScreen
                                        StatsScreen.scoreSS = score; //sending score information to StatsScreen

                                        Intent intent = new Intent(getApplicationContext(), StartScreen.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        password_var.setError("Incorrect Password");
                                    }
                                } else {
                                    username_var.setError("User does not exist");
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                //purposely nothing here
                            }
                        });



                    }else {
                        password_var.setError("Please enter password");
                    }
                }else {
                    username_var.setError("Please enter username");
                    if(password_.isEmpty()) password_var.setError("Please enter a password");
                    else{
                        password_var.setError(null);
                        password_var.setErrorEnabled(false);
                    }
                }

            }
        });

        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignUpScreen.class);
                startActivity(intent);
                finish();
            }
        });

    }
}