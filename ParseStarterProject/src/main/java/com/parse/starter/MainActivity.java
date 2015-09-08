/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.parse.ParseAnalytics;
import com.parse.starter.activities.AgendaActivity;
import com.parse.starter.activities.LoginActivity;
import com.parse.starter.activities.MapsActivity;
import com.parse.starter.activities.RaffleActivity;
import com.parse.starter.activities.SpeakersActivity;


public class MainActivity extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ParseAnalytics.trackAppOpenedInBackground(getIntent());
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

  public void onAgenda(View view) {
    Intent agendaIntent = new Intent(MainActivity.this,AgendaActivity.class);
    startActivity(agendaIntent);
  }

  public void onSpeakers(View view) {
    Intent speakersIntent = new Intent(MainActivity.this,SpeakersActivity.class);
    startActivity(speakersIntent);
  }

  public void onLogin(View view) {
    Intent loginIntent = new Intent(MainActivity.this,LoginActivity.class);
    startActivity(loginIntent);
  }

  public void onRaffle(View view) {
    Intent raffleIntent = new Intent(MainActivity.this,RaffleActivity.class);
    startActivity(raffleIntent);
  }

  public void onMap(View view) {
    Intent mapIntent = new Intent(MainActivity.this,MapsActivity.class);
    startActivity(mapIntent);
  }
}
