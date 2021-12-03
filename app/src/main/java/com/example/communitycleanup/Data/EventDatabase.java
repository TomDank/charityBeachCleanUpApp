package com.example.communitycleanup.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.communitycleanup.DataTransfer.Event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

/**An EventDatabase class which manages an SQLite database for the Events table
 *
 */
public class EventDatabase extends SQLiteOpenHelper {

    /**Create database object - discarding any events table which currently exists
     * and creating a new Events table
     */
    public EventDatabase(Context context)
    {
        super(context,"events.db",null,1);
        SQLiteDatabase db = this.getWritableDatabase();
        onUpgrade(db,0,0);
    }

    /**Create the table events, if it does not already exist*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS EVENTS(E_ID INTEGER PRIMARY KEY AUTOINCREMENT,DESCRIPTION VARCHAR(320) NOT NULL,LOCATION VARCHAR(320) NOT NULL,DATE VARCHAR(10) NOT NULL UNIQUE,START VARCHAR(10) NOT NULL, FINISH VARCHAR(10) NOT NULL,FAVOURITE VARCHAR(10) NOT NULL);");
    }

    /**Drop the Events table and call onCreate on the database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db,int a,int b) {
        db.execSQL("DROP TABLE IF EXISTS EVENTS");
        onCreate(db);
    }

    /**Insert a row into the event table
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
        db.insert("EVENTS",null,contentValues);
    }

    /**fills the events table with initial data if it is not already present
     */
    public void populate()
    {
        insert("April Cleanup","Beach House","04/04/20","10:00","12:00","No");
        insert("May Cleanup","North Hut","03/05/20","10:00","12:00","No");
        insert("June Cleanup","South Hut","06/06/20","10:00","12:00","No");
        insert("Test Another Event","Calum's Flat","16/03/20","21:00","23:00","No");
    }

    /**returns an ArrayList of all Event objects represented on the Events table
     *
     * @return ArrayList of Event objects
     */
    public ArrayList<Event> getEventsList() {
        ArrayList<Event> fullList = new ArrayList<Event>();
        Event thisEvent = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;

        try {
            c = db.rawQuery("Select * from Events", null);
            if (c.moveToFirst()) {
                do {
                    thisEvent = new Event();
                    thisEvent.setDescription(c.getString(c.getColumnIndex("DESCRIPTION")));
                    thisEvent.setLocation(c.getString(c.getColumnIndex("LOCATION")));
                    thisEvent.setDate(c.getString(c.getColumnIndex("DATE")));
                    thisEvent.setStart(c.getString(c.getColumnIndex("START")));
                    thisEvent.setFinish(c.getString(c.getColumnIndex("FINISH")));
                    thisEvent.setFavourite(c.getString(c.getColumnIndex("FAVOURITE")));
                    fullList.add(thisEvent);
                } while (c.moveToNext());
            }
            return fullList;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    /**Converts the hour part of the start/finish times stored in the Event Database to
     * an integer hour
     *
     * @param time the time string, eg 10:00
     * @return the integer hour eg 10
     */
    private int stringToHour(String time)
    {
        String[] splitTime = time.split(":");
        return Integer.parseInt(splitTime[0]);
    }

    /**Method to establish if an Event is currently running in the event database according
     * to the system time. A cursor goes through the event database looking for a match
     * with today's date, if any of the matches have start/finish hour parameters surrounding
     * the system time, the entire event object is returned. This assumes that all times
     * in the Event database have zero minute values - the method currently does not work with
     * minutes.
     *
     * @return The event object matching the current system time, or null
     */
    public Event isEventRunning()
    {
        Date today = new Date();
        int hour = today.getHours();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        String todayString = dateFormat.format(today);
        SQLiteDatabase db = this.getReadableDatabase();
        Event currentEvent = null;
        Cursor c = null;

        try {
            c = db.rawQuery("Select * from Events where date = '"+todayString+"';", null);
            if (c.moveToFirst()) {
                do {
                    int startHour = stringToHour(c.getString(c.getColumnIndex("START")));
                    int finishHour = stringToHour(c.getString(c.getColumnIndex("FINISH")));
                    if ((hour >= startHour) && (hour < finishHour)) {
                        currentEvent = new Event();
                        currentEvent.setDescription(c.getString(c.getColumnIndex("DESCRIPTION")));
                        currentEvent.setLocation(c.getString(c.getColumnIndex("LOCATION")));
                        currentEvent.setDate(c.getString(c.getColumnIndex("DATE")));
                        currentEvent.setStart(c.getString(c.getColumnIndex("START")));
                        currentEvent.setFinish(c.getString(c.getColumnIndex("FINISH")));
                        currentEvent.setFavourite(c.getString(c.getColumnIndex("FAVOURITE")));
                    }
                } while (c.moveToNext());
            }
        } finally {
            if (c != null) {
                c.close();
            }
        }

        return currentEvent;
    }
}
