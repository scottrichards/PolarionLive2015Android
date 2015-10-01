package com.polarion.starter.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.polarion.starter.adapters.DrawerAdapter;
import com.polarion.starter.model.DrawerItem;
import com.polarion.starter.model.MenuChoices;

/**
 * Created by scottrichards on 9/10/15.
 */
public class DrawerActionBarActivity extends ActionBarActivity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    protected DrawerAdapter mDrawerAdapter;
    private CharSequence mTitle;
    private CharSequence mDrawerTitle;
    private Toolbar mToolbar;

    // call this to setup the ActionBar with Navigation Drawer
    public void setupActionBar()
    {
        mTitle = mDrawerTitle = getTitle();

        mDrawerList = (ListView) findViewById(com.polarion.starter.R.id.left_drawer);
        mToolbar = (Toolbar) findViewById(com.polarion.starter.R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(com.polarion.starter.R.id.drawer_layout);


        mDrawerAdapter = new DrawerAdapter(this);
        mDrawerList.setAdapter(mDrawerAdapter);

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());


        if (mToolbar != null) {
            mToolbar.setTitle("Polarion Live 2015");
            setSupportActionBar(mToolbar);
        }
        initDrawer();
    }


    private void initDrawer() {

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, com.polarion.starter.R.string.drawer_open, com.polarion.starter.R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.d("MainActivity", "Clicked in Drawer Position: " + position);
            switch (position) {
                case MenuChoices.AGENDA :  mDrawerList.setItemChecked(position, true);
                    DrawerItem drawerItem = mDrawerAdapter.getItem(position);
                    if (drawerItem != null)
                        setTitle(drawerItem.title);
                    //             mDrawerLayout.closeDrawer(mDrawerList);
                    Intent agendaIntent = new Intent(DrawerActionBarActivity.this,AgendaActivity.class);
                    startActivity(agendaIntent);
                    finish();
                    break;
                case MenuChoices.SPEAKERS :  Intent speakersIntent = new Intent(DrawerActionBarActivity.this,SpeakersActivity.class);
                    startActivity(speakersIntent);
                    finish();
                    break;
                case MenuChoices.MAP :  Intent mapIntent = new Intent(DrawerActionBarActivity.this,MapsActivity.class);
                    startActivity(mapIntent);
                    finish();
                    break;
                case MenuChoices.RAFFLE :  Intent raffleIntent = new Intent(DrawerActionBarActivity.this,RaffleActivity.class);
                    startActivity(raffleIntent);
                    finish();
                    break;
                case MenuChoices.LOGIN :  Intent loginIntent = new Intent(DrawerActionBarActivity.this,LoginActivity.class);
                    startActivity(loginIntent);
                    finish();
                    break;
            }
            //     selectItem(position);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
