package com.example.communitycleanup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.communitycleanup.Data.CheckInDatabase;
import com.example.communitycleanup.Data.EventDatabase;
import com.example.communitycleanup.HomeActivities.AboutActivity;
import com.example.communitycleanup.HomeActivities.CheckIn.CheckInActivity;
import com.example.communitycleanup.HomeActivities.CheckIn.NoEventsActivity;
import com.example.communitycleanup.HomeActivities.CheckOut.CheckOutActivity;
import com.example.communitycleanup.HomeActivities.CheckOut.NoCheckOut;
import com.example.communitycleanup.HomeActivities.DrugEvidenceActivity;
import com.example.communitycleanup.HomeActivities.EventsActivity;
import com.example.communitycleanup.HomeActivities.MyAccount;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

/**The HomeScreen class - contains tiles which re-direct to the various
 * sub-menus of the application
 */
public class HomeScreen extends AppCompatActivity {
    EventDatabase eDB;
    CheckInDatabase cDB;
    /**Set the layout to the home screen activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        setTitle("Home");

        eDB = new EventDatabase(this);
        eDB.populate();
        cDB = new CheckInDatabase(this);
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
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**Open the EventsActivity
     *
     * @param view the "show upcoming events" button
     */
    public void events(View view)
    {
        Intent intent = new Intent(getApplicationContext(), EventsActivity.class);
        startActivity(intent);
    }

    /**Open the DrugEvidenceActivity
     *
     * @param view the "report drug evidence" button
     */
    public void ReportDrug(View view)
    {
        Intent intent = new Intent(getApplicationContext(), DrugEvidenceActivity.class);
        startActivity(intent);
    }
    

    /**Open the CheckInActivity
     *
     * @param view the "check in" button
     */
    public void checkIn(View view) {
        if (eDB.isEventRunning()==null){
            Intent intent = new Intent(getApplicationContext(), NoEventsActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(getApplicationContext(), CheckInActivity.class);
            startActivity(intent);
        }
    }

    /**Open the CheckOut activity
     *
     * @param view the "check out" button
     */
    public void checkOut(View view) {
        if (cDB.getCheckedInEvent()==null){
            Intent intent = new Intent(getApplicationContext(), NoCheckOut.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(getApplicationContext(), CheckOutActivity.class);
            startActivity(intent);
        }
    }

    /**Open the About activity
     *
     * @param view the "about us" button
     */
    public void about(View view) {
        Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
        startActivity(intent);
    }

    public void account(View view)
    {
        Intent intent = new Intent(getApplicationContext(), MyAccount.class);
        startActivity(intent);
    }
}
