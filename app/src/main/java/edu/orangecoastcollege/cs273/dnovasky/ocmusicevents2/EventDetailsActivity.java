package edu.orangecoastcollege.cs273.dnovasky.ocmusicevents2;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class EventDetailsActivity extends AppCompatActivity {

    private ImageView mEventImageView;
    private TextView mEventTitleTextView;
    private TextView mEventDateTextView;
    private TextView mEventTimeTextView;
    private TextView mEventLocationTextView;
    private TextView mEventAddress1TextView;
    private TextView mEventAddress2TextView;

    private Context context = (Context)this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        // Get the data from Intent
        Intent detailsIntent = getIntent();
        String title = detailsIntent.getStringExtra("Title");
        String date = detailsIntent.getStringExtra("Date");
        String day = detailsIntent.getStringExtra("Day");
        String time = detailsIntent.getStringExtra("Time");
        String location = detailsIntent.getStringExtra("Location");
        String address1 = detailsIntent.getStringExtra("Address1");
        String address2 = detailsIntent.getStringExtra("Address2");
        String imageFileName = title.replace(" ", "") + ".jpeg";

        // Connect the member variables to the UI view components
        mEventImageView = (ImageView) findViewById(R.id.eventImageView);
        mEventTitleTextView = (TextView) findViewById(R.id.eventTitleTextView);
        mEventDateTextView = (TextView) findViewById(R.id.eventDateDayTextView);
        mEventTimeTextView = (TextView) findViewById(R.id.eventTimeTextView);
        mEventLocationTextView = (TextView) findViewById(R.id.eventLocationTextView);
        mEventAddress1TextView = (TextView) findViewById(R.id.eventAddress1TextView);
        mEventAddress2TextView = (TextView) findViewById(R.id.eventAddress2TextView);

        // Create an asset manager to access the event images
        AssetManager am = context.getAssets();


        // Try to create a stream using the image file name. Next use that stream
        // to create a drawable. And finally, set our image view to the image associated
        // with the event.
        try
        {
            InputStream stream = am.open(imageFileName);
            Drawable drawable = Drawable.createFromStream(stream, title);
            mEventImageView.setImageDrawable(drawable);
        }

        // If there was an error we'll log an error message.
        catch (IOException e)
        {
            Log.e("OC Music Events", "Cannot load image: " + imageFileName + e);
        }

        // Set all the UI views using the associated event details that were retrieved from
        // the intent.
        mEventTitleTextView.setText(title);
        mEventDateTextView.setText(date + " - " + day);
        mEventTimeTextView.setText(time);
        mEventLocationTextView.setText(location);
        mEventAddress1TextView.setText(address1);
        mEventAddress2TextView.setText(address2);
    }
}
