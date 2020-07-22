//Nayan Pasari
//111868106
package com.example.hackmatcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.hackmatcher.App.MESSAGE;
/*
Helps to add a student to the database.
 */
public class HomeActivity extends AppCompatActivity {

    private SQLiteDatabase mDatabase;
    private HackAdapter mAdapter;
    private EditText mEditTextName;
    private EditText mEditTextSchool;
    private EditText mEditTextMajor;
    private EditText mEditTextGrad;
    private EditText mEditTextGender;
    private EditText mEditTextLang;
    private EditText mEditTextLink;
    private TextView mTextViewAmount;
    private TextView justlabel;
    private ImageView img;
    private Button mess;
    private int mAmount = 0;
    private NotificationManagerCompat notificationCompat;

    /*
    onCreate: Displays whatever is on the screen.
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        HackDBHelper dbHelper = new HackDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new HackAdapter(this, getAllItems());
        recyclerView.setAdapter(mAdapter);
        notificationCompat = NotificationManagerCompat.from(this);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            /*
            Helps the user to use the swipe feature to delete/remove data
             */
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                removeItem((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(recyclerView);


        mEditTextName = findViewById(R.id.edittext_name);
        mEditTextSchool = findViewById(R.id.edittext_school);
        mTextViewAmount = findViewById(R.id.textview_amount);
        mEditTextMajor = findViewById(R.id.edittext_major);
        mEditTextGrad = findViewById(R.id.edittext_gradyear);
        mEditTextGender = findViewById(R.id.edittext_gender);
        mEditTextLang = findViewById(R.id.edittext_languages);
        mEditTextLink = findViewById(R.id.edittext_links);
        img = findViewById(R.id.imageView);
        justlabel = findViewById(R.id.justtextlabel);
        mess = findViewById(R.id.button_message);
        Button buttonIncrease = findViewById(R.id.button_increase);
        Button buttonDecrease = findViewById(R.id.button_decrease);
        Button buttonAdd = findViewById(R.id.button_save);

        buttonIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increase();     //increases the Hackathon number
            }
        });
        buttonDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrease();     //decreases the Hackathon number
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();         //Saves the details
            }
        });

    }

    /*
    Helps to notify a student has been just added.
     */
    public void sendOnchannel(){
        String text =  "A new student is here!";
        Notification notification = new NotificationCompat.Builder(this, MESSAGE)
                .setSmallIcon(R.drawable.username)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentText(text)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationCompat.notify(1, notification);
    }

    /*
    Increases the mAmount
     */
    private void increase() {
        mAmount++;
        mTextViewAmount.setText(String.valueOf(mAmount));
    }

    /*
    Decreases the mAmount
     */
    private void decrease() {
        if (mAmount > 0) {
            mAmount--;
            mTextViewAmount.setText(String.valueOf(mAmount));
        }
    }

    /*
    Helps add a student with all the details filled
     */
    private void add() {
        if (mEditTextName.getText().toString().trim().length() == 0) {
            return;
        }
        String name = mEditTextName.getText().toString();
        String school = mEditTextSchool.getText().toString() + ",";
        String major = mEditTextMajor.getText().toString();
        int grad = Integer.parseInt(String.valueOf(mEditTextGrad.getText()));
        String gender = " " + mEditTextGender.getText().toString();
        String lang = "Prog Lang:" + mEditTextLang.getText().toString();
        String link = "Link:" + mEditTextLink.getText().toString();

        ContentValues cv = new ContentValues();         //to put all the info user filled
        cv.put(HackContract.HackEntry.COLUMN_NAME, name);
        cv.put(HackContract.HackEntry.COLUMN_SCHOOL, school);
        cv.put(HackContract.HackEntry.COLUMN_AMOUNT, mAmount);
        cv.put(HackContract.HackEntry.COLUMN_MAJOR, major);
        cv.put(HackContract.HackEntry.COLUMN_GRAD, grad);
        cv.put(HackContract.HackEntry.COLUMN_GENDER, gender);
        cv.put(HackContract.HackEntry.COLUMN_LANG, lang);
        cv.put(HackContract.HackEntry.COLUMN_LINK, link);
        mDatabase.insert(HackContract.HackEntry.TABLE_NAME, null, cv);
        mAdapter.swapCursor(getAllItems());

        mEditTextName.getText().clear();        //clears up all the edit fields
        mEditTextSchool.getText().clear();
        mEditTextMajor.getText().clear();
        mEditTextGrad.getText().clear();
        mEditTextGender.getText().clear();
        mEditTextLang.getText().clear();
        mEditTextLink.getText().clear();

        closekeyboard();                    //closes the keyboard on the save button click
        sendOnchannel();                    //notifies the user

    }

    //Deletes the user info by swiping left/right
    private void removeItem(long id) {
        mDatabase.delete(HackContract.HackEntry.TABLE_NAME,
                HackContract.HackEntry._ID + "=" + id, null);
        mAdapter.swapCursor(getAllItems());
    }

    private Cursor getAllItems() {
        return mDatabase.query(
                HackContract.HackEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                HackContract.HackEntry.COLUMN_TIMESTAMP + " DESC"       //places the newly added data up
        );
    }

    /*
    Closes the keyboard on clicking the button.
     */
    private void closekeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
