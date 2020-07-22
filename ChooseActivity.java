//Nayan Pasari
//ID: 111868106
package com.example.hackmatcher;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/*
A screen that allows to choose between different activities to look to.
 */
public class ChooseActivity extends AppCompatActivity {

    private Button meet;
    private Button help;

    /*
    onCreate: Displays whatever is included on the screen at build.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        meet = findViewById(R.id.buttonmeet);
        help = findViewById(R.id.buttonhelp);
        meet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent open = new Intent(ChooseActivity.this, HomeActivity.class);  //helps to open a new screen/intent
                startActivity(open);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent helpreq = new Intent(ChooseActivity.this, HelpActivity.class);   //helps to open a new screen/intent
                startActivity(helpreq);
            }
        });
    }
}
