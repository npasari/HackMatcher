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
Helps the user to Register and make an account
 */
public class RegisterActivity extends AppCompatActivity {

    EditText mTxtUsername, mTxtPassword, cfnPassword;
    Button RegisterBtn;
    TextView login;
    DatabaseHelper db;

    /*
    onCreate: Displays whatever is on the screen.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);

        mTxtUsername = findViewById(R.id.edittext_username);
        mTxtPassword = findViewById(R.id.edittext_password);
        cfnPassword = findViewById(R.id.edittext_cfn_password);
        RegisterBtn = findViewById(R.id.button_register);
        login = findViewById(R.id.textview_login);

        //Helps the user to Register and go to the Login page
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Login = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(Login);
            }
        });

        //Helps the user to check if the Register username and password is valid and helps to login
        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mTxtUsername.getText().toString();
                String pass = mTxtPassword.getText().toString();
                String cfn_pass = cfnPassword.getText().toString();

                if (pass.equals(cfn_pass)) {
                    Boolean checkuser = db.checkUser(user);
                    if (checkuser == true) {
                        Boolean insert = db.insert(user, pass);
                        if (insert == true) {
                            Toast.makeText(RegisterActivity.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                            Intent moveToLogin = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(moveToLogin);
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
