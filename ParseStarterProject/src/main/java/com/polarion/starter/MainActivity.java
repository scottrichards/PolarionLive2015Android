/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.polarion.starter;

//import android.app.Activity;
import android.content.res.Configuration;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

//import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.ActionBarActivity;


import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.parse.ParseAnalytics;
import com.parse.ParseUser;
import com.polarion.starter.activities.AgendaDetailsActivity;
import com.polarion.starter.adapters.DrawerAdapter;
import com.polarion.starter.fragments.AgendaItemFragment;
import com.polarion.starter.fragments.HomeFragment;
import com.polarion.starter.fragments.LocationFragment;
import com.polarion.starter.fragments.LoginFragment;
import com.polarion.starter.fragments.RaffleFragment;
import com.polarion.starter.fragments.SignupFragment;
import com.polarion.starter.fragments.SocialFragment;
import com.polarion.starter.fragments.SpeakerItemFragment;
import com.polarion.starter.fragments.WebFragment;
import com.polarion.starter.model.AgendaItem;
import com.polarion.starter.model.DrawerItem;

import com.polarion.starter.model.MenuChoices;

public class MainActivity extends ActionBarActivity
                          implements SocialFragment.OnFragmentInteractionListener,
                                        WebFragment.OnFragmentInteractionListener,
        RaffleFragment.OnFragmentInteractionListener,
        LoginFragment.OnFragmentInteractionListener,
        AgendaItemFragment.OnFragmentInteractionListener,
        SpeakerItemFragment.OnFragmentInteractionListener,
        SignupFragment.OnFragmentInteractionListener,
        HomeFragment.OnFragmentInteractionListener
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
      selectItem(MenuChoices.HOME);
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

//  @Override
//  public boolean onCreateOptionsMenu(Menu menu) {
//    // Inflate the menu; this adds items to the action bar if it is present.
//    getMenuInflater().inflate(R.menu.menu_main, menu);
//    return true;
//  }
//
//  @Override
//  public boolean onOptionsItemSelected(MenuItem item) {
//    // Handle action bar item clicks here. The action bar will
//    // automatically handle clicks on the Home/Up button, so long
//    // as you specify a parent activity in AndroidManifest.xml.
//    int id = item.getItemId();
//
//    //noinspection SimplifiableIfStatement
//    if (id == R.id.action_settings) {
//      return true;
//    }
//
//    return super.onOptionsItemSelected(item);
//  }

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
      case "RaffleFragment" :
          selectItem(MenuChoices.RAFFLE);
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
      case "LoginFragment" :
        selectItem(MenuChoices.LOGIN);
        break;
    }
  }

  @Override
  public void onSelectAgendaItem(AgendaItem item)
  {
    Log.d("MainActivity", "OnSelectAgendaItem - Description: " + item.getSessionName());
    StarterApplication myApp = (StarterApplication)getApplication();
    myApp.setSelectedAgendaItem(item);
    Intent agendaDetailsActivity= new Intent(MainActivity.this, AgendaDetailsActivity.class);
//    webIntent.putExtra(WebActivity.ARG_URL_PARAM, URLService.buildUrl("rules.html"));
//    webIntent.putExtra(WebActivity.ARG_TITLE_PARAM, "Raffle Rules");
    startActivity(agendaDetailsActivity);

//    FragmentManager fragmentManager = getFragmentManager();
//    Fragment fragment;
//    fragment = new AgendaDetailFragment();
//    fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(AgendaDetailFragment.class.getSimpleName()).commit();
  }

  @Override
  public void onSelectSpeakerItem(String id)
  {
    Log.d("MainActivity", "OnSelectSpeaker - id: " + id);

  }

  @Override
  public void onSignup() {
    Log.d("MainActivity", "onSignUp " );
    selectItem(MenuChoices.LOGIN);
  }

  @Override
  public void onHomeClicked() {
    Log.d("MainActivity", "onHomeClicked" );
    selectItem(MenuChoices.AGENDA);
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
      Log.d("MainActivity", "Clicked in Drawer Position: " + position);

        selectItem(position);
    }
  }

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
      case MenuChoices.HOME:
         fragment = new HomeFragment();

        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(HomeFragment.class.getSimpleName()).commit();
        break;
      case MenuChoices.AGENDA :
        fragment = new AgendaItemFragment();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(AgendaItemFragment.class.getSimpleName()).commit();
        break;
      case MenuChoices.SPEAKERS :
        fragment = new SpeakerItemFragment();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(SpeakerItemFragment.class.getSimpleName()).commit();
        break;
      case MenuChoices.PARTNERS :
        fragment = new WebFragment();
        args = new Bundle();
        args.putString(WebFragment.ARG_URL_PARAM, "http://54.183.27.217/2015m/partners/index.html");
        args.putString(WebFragment.ARG_TITLE_PARAM, "Partners");
        fragment.setArguments(args);
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(LocationFragment.class.getSimpleName()).commit();
        break;

      case MenuChoices.MAP :
        fragment = new LocationFragment();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(LocationFragment.class.getSimpleName()).commit();
        break;
      case MenuChoices.RAFFLE :
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser == null) {  // if user is not logged in then force login before entering raffle
          fragment = new LoginFragment();
          args = new Bundle();
          args.putString(LoginFragment.FROM_ACTIVITY, RaffleFragment.class.getSimpleName());
          fragment.setArguments(args);
          setTitle("Log In");
          fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(LoginFragment.class.getSimpleName()).commit();
        } else {
          fragment = new RaffleFragment();
          fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(RaffleFragment.class.getSimpleName()).commit();
        }
        break;
      case MenuChoices.LOGIN :
        fragment = new LoginFragment();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(LoginFragment.class.getSimpleName()).commit();
        break;

      case MenuChoices.SOCIAL :
        fragment = new SocialFragment();
//        args = new Bundle();
//        args.putInt(DrawerFragment.ARG_DRAWER_NUMBER, position);
//        fragment.setArguments(args);
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        break;

//      default :
//        fragment = new DrawerFragment();
//        args = new Bundle();
//        args.putInt(DrawerFragment.ARG_DRAWER_NUMBER, position);
//        fragment.setArguments(args);
//        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
//        break;
    }
    // update selected item and title, then close the drawer
    mDrawerList.setItemChecked(position, true);

    if (drawerItem != null)
      setTitle(drawerItem.title);
    mDrawerLayout.closeDrawer(mDrawerList);
  }

  @Override
  public void setTitle(CharSequence title) {
    if (mToolbar != null) {
      mToolbar.setTitle(title);
    }
  }

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
    public static final String ARG_DRAWER_ADAPTER = "drawer_adapter";
    public static final String ARG_DRAWER_NUMBER = "drawer_number";
    private DrawerAdapter mDrawerAdapter;

    public DrawerFragment() {

    }

//    public DrawerFragment(DrawerAdapter drawerAdapter) {
//      // Empty constructor required for fragment subclasses
//      mDrawerAdapter = drawerAdapter;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View rootView = inflater.inflate(R.layout.fragment_drawer, container, false);
      int position = getArguments().getInt(ARG_DRAWER_NUMBER);

//      mDrawerAdapter = (DrawerAdapter)getArguments().get(ARG_DRAWER_ADAPTER);
//
//      DrawerItem drawerItem = mDrawerAdapter.getItem(position);
//      if (drawerItem != null)
//      {
//        String planet = drawerItem.title;
//        int imageId = drawerItem.resourceId;
//        ((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);
//        getActivity().setTitle(planet);
//      }
      return rootView;
    }
  }
}
