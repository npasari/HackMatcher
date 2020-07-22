//Nayan Pasari
//111868106
package com.example.hackmatcher;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/*
Main Activity: Helps the user to Login to the app
 */
public class MainActivity extends AppCompatActivity {

    EditText mTxtUsername, mTxtPassword;
    Button loginbtn;
    TextView register;
    DatabaseHelper db;

    /*
    onCreate: Displays the data on the screen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        mTxtUsername = findViewById(R.id.edittext_username);
        mTxtPassword = findViewById(R.id.edittext_password);
        loginbtn = findViewById(R.id.button_login);
        register = findViewById(R.id.textview_register);

        //after clicking on the register textview below button, it helps you to go to the Register page
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Rintent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(Rintent);
            }
        });

        //Checks if the login is correct or you need to register
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mTxtUsername.getText().toString();
                String pass = mTxtPassword.getText().toString();
                Boolean res = db.checkUserPass(user, pass);
                if (res == true) {
                    Toast.makeText(MainActivity.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                    Intent Screen = new Intent(MainActivity.this, ChooseActivity.class);
                    startActivity(Screen);
                } else {
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}