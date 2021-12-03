package com.example.communitycleanup.Data;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.communitycleanup.DataTransfer.LogAnIssue;

public class LogIssueDatabase extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Reported Issue";
    private static final String TABLE_NAME = "Issue";
    private static final String KEY_ID = "id";
    //private static final String KEY_User = "User_Email";
    private static final String KEY_Area = "Area Postcode";
    private static final String KEY_Details = "Details";
    private static final String[] COLUMNS = { KEY_ID, KEY_Area,
            KEY_Details };

    public LogIssueDatabase (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //String CREATION_TABLE = "CREATE TABLE Issues ( "
        //        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
        //        + "Postcode TEXT, " + "Details TEXT )";
        //db.execSQL(CREATION_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you can implement here migration process
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);

    }

    public void addIssue(LogAnIssue evidence) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(KEY_User, evidence.userEmail);
        //values.put(KEY_Area, evidence.postcode);
        values.put(KEY_Details, evidence.description);
        // insert
        db.insert(TABLE_NAME,null, values);
        db.close();
    }

}
