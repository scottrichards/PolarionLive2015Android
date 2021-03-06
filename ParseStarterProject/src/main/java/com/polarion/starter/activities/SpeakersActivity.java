package com.polarion.starter.activities;

// import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.polarion.starter.adapters.SpeakersAdapter;
import com.polarion.starter.model.Speaker;

import java.util.ArrayList;
import java.util.List;


public class SpeakersActivity extends Activity {
    private SpeakersAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.polarion.starter.R.layout.activity_speakers);

        ParseObject.registerSubclass(Speaker.class);

        mAdapter = new SpeakersAdapter(this, new ArrayList<Speaker>());

        ListView listView = (ListView) findViewById(com.polarion.starter.R.id.listView);
        listView.setAdapter(mAdapter);
        updateData();
    }

    public void updateData(){
        ParseQuery<Speaker> query = ParseQuery.getQuery(Speaker.class);
        query.findInBackground(new FindCallback<Speaker>() {
            @Override
            public void done(List<Speaker> speakers, ParseException error) {
                if (speakers != null) {
                    mAdapter.clear();
                    for (int i = 0; i < speakers.size(); i++) {
                        mAdapter.add(speakers.get(i));
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.polarion.starter.R.menu.menu_speakers, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == com.polarion.starter.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
