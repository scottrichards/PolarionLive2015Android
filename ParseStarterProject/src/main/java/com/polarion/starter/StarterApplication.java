/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.polarion.starter;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.polarion.starter.model.AgendaItem;


public class StarterApplication extends Application {

  private AgendaItem selectedItem;

  @Override
  public void onCreate() {
    super.onCreate();

    // Enable Local Datastore.
    Parse.enableLocalDatastore(this);

    // Add your initialization code here
    Parse.initialize(this, "ytC7lrxAdRDvguofSHtRLyPe8DdiO5MdC4VdMDoz", "GIX1MZRR1rPqQGEOTEKwyNa6FuHWLhuiy3HyrVPt");


//    ParseUser.enableAutomaticUser();  // This causes getCurrentUser to always return a user
    ParseACL defaultACL = new ParseACL();
    // Optionally enable public read access.
    // defaultACL.setPublicReadAccess(true);
    ParseACL.setDefaultACL(defaultACL, true);
  }

  public void setSelectedAgendaItem(AgendaItem item) {
    selectedItem = item;
  }

  public AgendaItem getSelectedAgendaItem() {
    return selectedItem;
  }
}
