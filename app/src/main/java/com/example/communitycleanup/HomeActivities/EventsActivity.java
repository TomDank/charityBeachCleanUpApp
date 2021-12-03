package com.example.communitycleanup.HomeActivities;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.communitycleanup.Data.EventDatabase;
import com.example.communitycleanup.DataTransfer.Event;
import com.example.communitycleanup.MainActivity;
import com.example.communitycleanup.R;

import java.util.ArrayList;

/**The View Upcoming Events activity class
 */
public class EventsActivity extends AppCompatActivity {

    /**On creation, set the content view to the layout stored in the Events Activity
     * Initialise the Events database from the EventDatabase class and populate the database with
     * data. Create an ArrayList of Event which pulls the Events from the database.
     *
     * Fill the table on the Events Activity with 10 new rows representing Events.
     * Where an event can be represented by a valid index in the Events Array List, fill
     * the table row with valid data.
     *
     * The weights of the TextView boxes are controlled to ensure no formatting errors occur on the UI
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        setTitle("View Upcoming Events");
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        catch (NullPointerException e){
            System.out.println("null pointer exception");
        }

        EventDatabase eDB = new EventDatabase(this);
        eDB.populate();
        ArrayList<Event> myEventList = new ArrayList<Event>();
        myEventList = eDB.getEventsList();

        TableLayout tb = findViewById(R.id.tableLayout);
        tb.setStretchAllColumns(true);

        for(int i=0;(i<10);i++)
        {
            TableRow tr = new TableRow(this);
            tr.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT,1f));

            TextView[] textViewArray = {null,null,null,null,null};
            float[] weights = {1.0f,1.0f,0.7f,0.5f,0.7f};

            for (int j=0;j<5;j++)
            {
                textViewArray[j] = new TextView(this);
                textViewArray[j].setLayoutParams(new TableRow.LayoutParams(0,TableRow.LayoutParams.WRAP_CONTENT,weights[j]));
                tr.addView(textViewArray[j]);
            }

            if (i<myEventList.size()) {
                //Future Code:

                /*CheckBox myCheckBox = new CheckBox(this);
                myCheckBox.setLayoutParams(new TableRow.LayoutParams(0,TableRow.LayoutParams.WRAP_CONTENT,0.5f));
                tr.addView(myCheckBox);*/

                textViewArray[0].setText(myEventList.get(i).getDescription());
                textViewArray[1].setText(myEventList.get(i).getLocation());
                textViewArray[2].setText(myEventList.get(i).getDate());
                textViewArray[3].setText(myEventList.get(i).getStart());
                textViewArray[4].setText(myEventList.get(i).getFinish());
            }
            tb.addView(tr);
        }
    }

    /**Override the default action bar
     *
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.usual_menu,menu);
        return true;
    }

    /**Define options menu behaviour
     *
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.item2:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
