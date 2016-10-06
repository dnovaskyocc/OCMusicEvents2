package edu.orangecoastcollege.cs273.dnovasky.ocmusicevents2;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class EventListActivity extends ListActivity {

    private ArrayList<MusicEvent> allMusicEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Try to load the list of events from the JSON file
        try
        {
            allMusicEvents = JSONLoader.loadJSONFromAsset(this);
        }
        // If there's an IOException we'll log an error message
        catch(IOException e)
        {
            Log.e("OC Music Events", "Error loading JSON data." + e.getMessage());
        }
        // Set the list adapter to use our custom list layout.
        setListAdapter(new MusicEventAdapter(this, R.layout.music_event_list_item, allMusicEvents));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int pos, long id) {

        //  1) Use the position to get the details from the clicked event.
        MusicEvent clickedEvent = allMusicEvents.get(pos);

        String title = clickedEvent.getTitle();
        String date = clickedEvent.getDate();
        String day = clickedEvent.getDay();
        String time = clickedEvent.getTime();
        String location = clickedEvent.getLocation();
        String address1 = clickedEvent.getAddress1();
        String address2 = clickedEvent.getAddress2();

        // 2) Create a new intent.
        Intent intent = new Intent(this, EventDetailsActivity.class);

        // 3) Pass the event details to the intent
        intent.putExtra("Title", title);
        intent.putExtra("Date", date);
        intent.putExtra("Day", day);
        intent.putExtra("Time", time);
        intent.putExtra("Location", location);
        intent.putExtra("Address1", address1);
        intent.putExtra("Address2", address2);

        // 4) Start the activity
        startActivity(intent);

    }
}
