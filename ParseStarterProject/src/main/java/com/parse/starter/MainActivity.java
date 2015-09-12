/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

//import android.app.Activity;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;

//import android.support.v4.view.GravityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.ActionBarActivity;


import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.parse.ParseAnalytics;
import com.parse.starter.activities.AgendaActivity;
import com.parse.starter.activities.LoginActivity;
import com.parse.starter.activities.MapsActivity;
import com.parse.starter.activities.RaffleActivity;
import com.parse.starter.activities.SpeakersActivity;
import com.parse.starter.adapters.DrawerAdapter;
import com.parse.starter.fragments.AgendaItemFragment;
import com.parse.starter.fragments.HomeFragment;
import com.parse.starter.fragments.LocationFragment;
import com.parse.starter.fragments.LoginFragment;
import com.parse.starter.fragments.RaffleFragment;
import com.parse.starter.fragments.SignupFragment;
import com.parse.starter.fragments.SocialFragment;
import com.parse.starter.fragments.SpeakerItemFragment;
import com.parse.starter.fragments.WebFragment;
import com.parse.starter.model.DrawerItem;
import com.parse.starter.utility.URLService;


public class MainActivity extends ActionBarActivity
                          implements SocialFragment.OnFragmentInteractionListener,
                                        WebFragment.OnFragmentInteractionListener,
                                      RaffleFragment.OnFragmentInteractionListener,
                                      LoginFragment.OnFragmentInteractionListener,
        AgendaItemFragment.OnFragmentInteractionListener,
        SpeakerItemFragment.OnFragmentInteractionListener,
        SignupFragment.OnFragmentInteractionListener
{

  private DrawerLayout mDrawerLayout;
  private ListView mDrawerList;
  private ActionBarDrawerToggle mDrawerToggle;
  protected DrawerAdapter mDrawerAdapter;
  private CharSequence mTitle;
  private CharSequence mDrawerTitle;
  private Toolbar mToolbar;
  private int mPreviousPosition, mCurrentPosition;
  public static FragmentManager mFragmentManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mTitle = mDrawerTitle = getTitle();

    mDrawerList = (ListView) findViewById(R.id.left_drawer);
    mToolbar = (Toolbar) findViewById(R.id.toolbar);
    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

    // set a custom shadow that overlays the main content when the drawer opens
//    mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
    // set up the drawer's list view with items and click listener
    mDrawerAdapter = new DrawerAdapter(this);
    mDrawerList.setAdapter(mDrawerAdapter);

    mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

    // enable ActionBar app icon to behave as action to toggle nav drawer
//    getActionBar().setDisplayHomeAsUpEnabled(true);
//    getActionBar().setHomeButtonEnabled(true);

//     ActionBarDrawerToggle ties together the the proper interactions
//     between the sliding drawer and the action bar app icon
//    mDrawerToggle = new ActionBarDrawerToggle(this,                  /* host Activity */
//            mDrawerLayout,         /* DrawerLayout object */
//            R.string.drawer_open,  /* "open drawer" description for accessibility */
//            R.string.drawer_close  /* "close drawer" description for accessibility */)

    if (mToolbar != null) {
      mToolbar.setTitle("Polarion Live 2015");
      setSupportActionBar(mToolbar);
    }
    initDrawer();
//    mDrawerToggle = new ActionBarDrawerToggle(
//            this,                  /* host Activity */
//            mDrawerLayout,         /* DrawerLayout object */
//            R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
//            R.string.drawer_open,  /* "open drawer" description for accessibility */
//            R.string.drawer_close  /* "close drawer" description for accessibility */
//    )
//      {
//      public void onDrawerClosed(View view) {
//        getActionBar().setTitle(mTitle);
//        invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
//      }
//
//      public void onDrawerOpened(View drawerView) {
//        getActionBar().setTitle(mDrawerTitle);
//        invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
//      }
//    };
//    mDrawerLayout.setDrawerListener(mDrawerToggle);

    if (savedInstanceState == null) {
      selectItem(0);
    }
    mFragmentManager = getFragmentManager();
    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }


  private void initDrawer() {

    mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close) {

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

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
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

  @Override
  public void onFragmentInteraction(Uri uri) {
    Log.d("MainActivity", "onFragmentInteraction: " + uri);
  }

  @Override
  public void onEnterRaffle() {
    Log.d("MainActivity", "OnEnter Raffle");
    popBackStack();
  }

  @Override
  public void onSignIn() {
    Log.d("MainActivity", "Signed In");
    popBackStack();
  }

  @Override
  public void onStartActivity(String className) {
    Log.d("MainActivity", "StartActivity: " + className);
    switch (className) {
      case "RaffleActivity" : selectItem(3);
          break;
      case "SignupFragment" :
          if (mToolbar != null) {
            mToolbar.setTitle("Sign Up");
          }
          Fragment fragment = new SignupFragment();
          mFragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
          break;
    }
  }

  // invoked from the SignupFragment
  @Override
  public void signupOnStartActivity(String className) {
    Log.d("MainActivity", "StartActivity: " + className);
    switch (className) {
      case "LoginFragment" : selectItem(5);
        break;
    }
  }

  @Override
  public void onSelectItem(String id)
  {
    Log.d("MainActivity", "OnSelectItem - id: " + id);
  }

  @Override
  public void onSelectSpeakerItem(String id)
  {
    Log.d("MainActivity", "OnSelectSpeaker - id: " + id);

  }

  @Override
  public void onSignup() {
    Log.d("MainActivity", "onSignUp " );
    selectItem(5);
  }

  // pop the last fragment of the stack and update the title
  public void popBackStack()
  {
    FragmentManager fragmentManager = getFragmentManager();
    fragmentManager.popBackStack();
    DrawerItem drawerItem = mDrawerAdapter.getItem(mPreviousPosition);
    if (mToolbar != null && drawerItem != null) {
      mToolbar.setTitle(drawerItem.title);
    }
  }

  /* The click listner for ListView in the navigation drawer */
  private class DrawerItemClickListener implements ListView.OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      Log.d("MainActivity","Clicked in Drawer Position: " + position);


//      switch (position) {
//        case 1 :  mDrawerList.setItemChecked(position, true);
//                  DrawerItem drawerItem = mDrawerAdapter.getItem(position);
//                  if (drawerItem != null)
//                    setTitle(drawerItem.title);
//                  mDrawerLayout.closeDrawer(mDrawerList);
//                  Intent agendaIntent = new Intent(MainActivity.this,AgendaActivity.class);
//                  startActivity(agendaIntent);
//                  finish();
//                  break;
//        case 2 :  Intent speakersIntent = new Intent(MainActivity.this,SpeakersActivity.class);
//                  startActivity(speakersIntent);
//                  finish();
//                  break;
//        case 3 :  Intent mapIntent = new Intent(MainActivity.this,MapsActivity.class);
//                  startActivity(mapIntent);
//                  finish();
//                  break;
//        case 4 :  Intent raffleIntent = new Intent(MainActivity.this,RaffleActivity.class);
//                  startActivity(raffleIntent);
//                  finish();
//                  break;
//        case 5 :  Intent loginIntent = new Intent(MainActivity.this,LoginActivity.class);
//                  startActivity(loginIntent);
//                  finish();
//                  break;
//        case 6: mDrawerList.setItemChecked(position, true);
//                drawerItem = mDrawerAdapter.getItem(position);
//                if (drawerItem != null)
//                  setTitle(drawerItem.title);
//                mDrawerLayout.closeDrawer(mDrawerList);
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                SocialFragment fragment = new SocialFragment();
//                fragmentTransaction.add(R.id.frame_contents, fragment);
//                fragmentTransaction.commit();
//                break;
//
//      }
        selectItem(position);
    }
  }
//
  private void selectItem(int position) {
    // update the main content by replacing fragments
    mPreviousPosition = mCurrentPosition;
    mCurrentPosition = position;
    DrawerItem drawerItem = mDrawerAdapter.getItem(position);
    if (mToolbar != null && drawerItem != null) {
      mToolbar.setTitle(drawerItem.title);
    }
    FragmentManager fragmentManager = getFragmentManager();
    Fragment fragment;
    Bundle args;
    switch (position) {
      case 0 :
         fragment = new HomeFragment();

        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(HomeFragment.class.getSimpleName()).commit();
        break;
      case 1 :
        fragment = new AgendaItemFragment();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(AgendaItemFragment.class.getSimpleName()).commit();
        break;
      case 2 :
        fragment = new SpeakerItemFragment();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(SpeakerItemFragment.class.getSimpleName()).commit();
        break;
      case 3 :
//        fragment = new WebFragment();
//        args = new Bundle();
//        args.putString(WebFragment.ARG_URL_PARAM, URLService.buildUrl("rules.html"));
//        fragment.setArguments(args);
//
//        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        fragment = new LocationFragment();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(LocationFragment.class.getSimpleName()).commit();
        break;
      case 4 :
        fragment = new RaffleFragment();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(RaffleFragment.class.getSimpleName()).commit();
        break;
      case 5 :
        fragment = new LoginFragment();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(LoginFragment.class.getSimpleName()).commit();
        break;

      case 6 :
        fragment = new SocialFragment();
        args = new Bundle();
        args.putInt(DrawerFragment.ARG_DRAWER_NUMBER, position);
        fragment.setArguments(args);
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        break;

      default :
        fragment = new DrawerFragment(mDrawerAdapter);
        args = new Bundle();
        args.putInt(DrawerFragment.ARG_DRAWER_NUMBER, position);
        fragment.setArguments(args);
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        break;
    }
    // update selected item and title, then close the drawer
    mDrawerList.setItemChecked(position, true);

    if (drawerItem != null)
      setTitle(drawerItem.title);
    mDrawerLayout.closeDrawer(mDrawerList);
  }

//  @Override
//  public void setTitle(CharSequence title) {
//    mTitle = title;
//    getActionBar().setTitle(mTitle);
//  }

  /**
   * When using the ActionBarDrawerToggle, you must call it during
   * onPostCreate() and onConfigurationChanged()...
   */
//  @Override
//  protected void onPostCreate(Bundle savedInstanceState) {
//    super.onPostCreate(savedInstanceState);
//    // Sync the toggle state after onRestoreInstanceState has occurred.
//    mDrawerToggle.syncState();
//  }
//
//  @Override
//  public void onConfigurationChanged(Configuration newConfig) {
//    super.onConfigurationChanged(newConfig);
//    // Pass any configuration change to the drawer toggls
//    mDrawerToggle.onConfigurationChanged(newConfig);
//  }
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



  /**
   * Fragment that appears in the "content_frame", shows a planet
   */
  public static class DrawerFragment extends Fragment {
    public static final String ARG_DRAWER_NUMBER = "drawer_number";
    private DrawerAdapter mDrawerAdapter;

    public DrawerFragment(DrawerAdapter drawerAdapter) {
      // Empty constructor required for fragment subclasses
      mDrawerAdapter = drawerAdapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View rootView = inflater.inflate(R.layout.fragment_drawer, container, false);
      int position = getArguments().getInt(ARG_DRAWER_NUMBER);

      DrawerItem drawerItem = mDrawerAdapter.getItem(position);
      if (drawerItem != null)
      {
        String planet = drawerItem.title;
        int imageId = drawerItem.resourceId;
        ((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);
        getActivity().setTitle(planet);
      }
      return rootView;
    }
  }
}
