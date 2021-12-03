package com.example.communitycleanup.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.communitycleanup.DataTransfer.Event;

/**An CheckInDatabase class for managing user check-in
 *
 */
public class CheckInDatabase extends SQLiteOpenHelper {

    /**Create database object - discarding any check-in table which currently exists
     * and creating a new check-in table
     */
    public CheckInDatabase(Context context)
    {
        super(context,"checkin.db",null,1);
        SQLiteDatabase db = this.getWritableDatabase();
        onCreate(db);
    }

    /**Create the table checkin, if it does not already exist*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS CHECKIN(E_ID INTEGER PRIMARY KEY AUTOINCREMENT,DESCRIPTION VARCHAR(320) NOT NULL,LOCATION VARCHAR(320) NOT NULL,DATE VARCHAR(10) NOT NULL UNIQUE,START VARCHAR(10) NOT NULL, FINISH VARCHAR(10) NOT NULL,FAVOURITE VARCHAR(10) NOT NULL);");
    }

    /**Drop the checkin table and call onCreate on the database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db,int a,int b) {
        db.execSQL("DROP TABLE IF EXISTS CHECKIN");
        onCreate(db);
    }

    /**Insert a row into the checkin table
     *
     * @param description a description of the event
     * @param location a location of the event
     * @param date a date the event takes place
     * @param start the start time of the event
     * @param finish the finish time of the event
     * @param isFavourite whether the event is a favourite event or not
     */
    public void insert(String description,String location,String date,String start,String finish,String isFavourite)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DESCRIPTION",description);
        contentValues.put("LOCATION",location);
        contentValues.put("DATE",date);
        contentValues.put("START",start);
        contentValues.put("FINISH",finish);
        contentValues.put("FAVOURITE",isFavourite);
        db.insert("CHECKIN",null,contentValues);
    }

    public void checkIn(Event myEvent)
    {
        this.insert(myEvent.getDescription(),myEvent.getLocation(),myEvent.getDate(),myEvent.getStart(),myEvent.getFinish(),myEvent.getFavourite());
    }

    public Event getCheckedInEvent()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Event thisEvent = null;
        Cursor c = null;
        try {
            c = db.rawQuery("Select * from checkin;", null);
            if (c.moveToFirst()) {
                do {
                    thisEvent = new Event();
                    thisEvent.setDescription(c.getString(c.getColumnIndex("DESCRIPTION")));
                    thisEvent.setLocation(c.getString(c.getColumnIndex("LOCATION")));
                    thisEvent.setDate(c.getString(c.getColumnIndex("DATE")));
                    thisEvent.setStart(c.getString(c.getColumnIndex("START")));
                    thisEvent.setFinish(c.getString(c.getColumnIndex("FINISH")));
                    thisEvent.setFavourite(c.getString(c.getColumnIndex("FAVOURITE")));
                } while (c.moveToNext());
            }
            return thisEvent;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    public void checkOut(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM CHECKIN;");
    }
}

