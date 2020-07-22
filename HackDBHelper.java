//Nayan Pasari
//111868106
package com.example.hackmatcher;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.hackmatcher.HackContract.*;

public class HackDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "hackmatcher.db";
    public static final int DATABASE_VERSION = 1;

    public HackDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*
    onCreate: Creates a table
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_HACKMATCHER_TABLE = "CREATE TABLE " +
                HackEntry.TABLE_NAME + " (" +
                HackEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HackEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                HackEntry.COLUMN_SCHOOL + " TEXT NOT NULL, " +
                HackEntry.COLUMN_AMOUNT + " INTEGER NOT NULL, " +
                HackEntry.COLUMN_MAJOR + " TEXT NOT NULL, " +
                HackEntry.COLUMN_GRAD + " INTEGER NOT NULL, " +
                HackEntry.COLUMN_GENDER + " TEXT NOT NULL, " +
                HackEntry.COLUMN_LANG + " TEXT NOT NULL, " +
                HackEntry.COLUMN_LINK + " TEXT NOT NULL, " +
                HackEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        db.execSQL(SQL_CREATE_HACKMATCHER_TABLE);
    }

    /*
    onUpgrade: This will be used when there's any changes to the version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + HackEntry.TABLE_NAME);
        onCreate(db);
    }
}
