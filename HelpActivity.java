package com.example.hackmatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*
Helps the user to give a feedback if facing any issues regarding app/in general
 */
public class HelpActivity extends AppCompatActivity implements Dialog.DialogListener {

    TextView txt1, txt2;
    Button dialogbutton;
    Button logout;

    /*
    onCreate: Displays whatever is included on the screen at build.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        txt1 = findViewById(R.id.textViewc1);
        txt2 = findViewById(R.id.textViewc2);
        dialogbutton = findViewById(R.id.dialog);
        logout = findViewById(R.id.button_logout);

        getSupportActionBar().setTitle("Home");         //back button at the top
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Helps the user to open a dialog
        dialogbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        //Helps the user to logout of the app/system
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent start = new Intent(HelpActivity.this, MainActivity.class);
                startActivity(start);
            }
        });
    }

    /*
    Opens a dialog
     */
    private void openDialog() {
        Dialog dialog = new Dialog();
        dialog.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void applyTexts(String issue, String solve) {
        txt1.setText(issue);
        txt2.setText(solve);
    }
}
