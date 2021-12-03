package com.example.communitycleanup.HomeActivities;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.communitycleanup.MainActivity;
import com.example.communitycleanup.R;



public class AboutActivity extends AppCompatActivity {
    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle("About Us");
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        catch (NullPointerException e){
            System.out.println("null pointer exception");
        }
        //get data passed in, using getStringExtra()

        Bundle bundle = getIntent().getExtras();
    }
    public void onClick (View view) {
        // use an intent object to return data
        Intent i = new Intent();

        //use the setData() method to return a value
        i.setData(Uri.parse("You are back on the home screen"));
        setResult(RESULT_OK, i);
        //destroy activity
        finish();
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