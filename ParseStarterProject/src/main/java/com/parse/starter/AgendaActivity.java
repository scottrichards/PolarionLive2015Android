package com.parse.starter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.starter.model.AgendaItem;
//import com.parse.ParseQueryAdapter;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AgendaActivity extends ActionBarActivity {
    private AgendaAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        ParseObject.registerSubclass(AgendaItem.class);

        mAdapter = new AgendaAdapter(this, new ArrayList<AgendaItem>());

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(mAdapter);
        updateData();
    }

    public void updateData(){
        ParseQuery<AgendaItem> query = ParseQuery.getQuery(AgendaItem.class);
//        query.setCachePolicy(ParseQuery.CachePolicy.CACHE_THEN_NETWORK);
        query.findInBackground(new FindCallback<AgendaItem>() {
            @Override
            public void done(List<AgendaItem> agendaItems, ParseException error) {
                if (agendaItems != null) {
                    mAdapter.clear();
                    for (int i = 0; i < agendaItems.size(); i++) {
                        mAdapter.add(agendaItems.get(i));
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_agenda, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
