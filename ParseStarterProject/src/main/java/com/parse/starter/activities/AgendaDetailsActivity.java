package com.parse.starter.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.starter.R;
import com.parse.starter.StarterApplication;
import com.parse.starter.model.AgendaItem;

public class AgendaDetailsActivity extends ActionBarActivity {

    private Toolbar mToolbar;
    private AgendaItem mItem;
    private GridLayout mRatingLayout;
    private Button mRateButton;

    // method so we can setup the item pointing to session info before starting this activity
    public void setAgendaItem(AgendaItem item) {
        mItem = item;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_details);

        // setup the Toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            mToolbar.setTitle("Session Details");
            setSupportActionBar(mToolbar);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // get the selected Agenda Item stored in Application
        StarterApplication myApp = (StarterApplication)getApplication();
        mItem =myApp.getSelectedAgendaItem();
        TextView sessionTime = (TextView)findViewById(R.id.sessionTime);
        sessionTime.setText(mItem.getDisplayTime());
        TextView descriptionView = (TextView)findViewById(R.id.sessionDescription);
        descriptionView.setText(mItem.getDescription());
        TextView sessionName = (TextView)findViewById(R.id.sessionName);
        sessionName.setText(mItem.getSessionName());
        TextView speakerView = (TextView)findViewById(R.id.presenter);
        speakerView.setText(mItem.getSpeaker());
        final RatingBar contentBar = (RatingBar)findViewById(R.id.contentBarGrid);
        final RatingBar presenterBar = (RatingBar)findViewById(R.id.presenterBarGrid);
        mRateButton = (Button)findViewById(R.id.rateButton);
        mRateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseObject sessionRating = new ParseObject("SessionRating");
                float contentRating = contentBar.getRating(), presenterRating = presenterBar.getRating();
                sessionRating.put("contentRating", contentRating);
                sessionRating.put("presenterRating", presenterRating);
                if (ParseUser.getCurrentUser() != null)
                    sessionRating.put("user", ParseUser.getCurrentUser());
                sessionRating.put("session",mItem);
                sessionRating.saveEventually();
                finish();   // when done rating close the Agenda Details
            }
        });
        Boolean isRateable = mItem.isRateable();
        if (!isRateable) {
            mRateButton.setVisibility(View.INVISIBLE);
            mRatingLayout = (GridLayout)findViewById(R.id.ratingGridLayout);
            mRatingLayout.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:     // Going Back Finish this activity to go back to the Agenda
                Log.d("AgendaDetailsActivity","Go Home");
                finish();
                return true;
            case R.id.action_settings:

                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
