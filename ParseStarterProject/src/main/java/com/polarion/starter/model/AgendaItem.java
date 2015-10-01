package com.polarion.starter.model;
import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;

/**
 * Created by scottrichards on 9/4/15.
 */
@ParseClassName("Agenda")
public class AgendaItem extends ParseObject {

    public int itemType;
    public Date date;

    public AgendaItem(){

    }

//    public boolean isCompleted(){
//        return getBoolean("completed");
//    }
//
//    public void setCompleted(boolean complete){
//        put("completed", complete);
//    }

    public String getSessionName(){
        return getString("session");
    }

    public void setSessionName(String session){
        put("session", session);
    }

    public String getSpeaker(){
        return getString("speaker");
    }

    public void setSpeaker(String speaker){
        put("speaker", speaker);
    }

    public String getDescription(){
        return getString("description");
    }

    public void setDescription(String description){
        put("description", description);
    }

    public String getDisplayTime(){
        return getString("displayTime");
    }

    public void setDisplayTime(String displayTime){
        put("displayTime", displayTime);
    }

    public String getLocation(){
        return getString("location");
    }

    public void setLocation(String location){
        put("location", location);
    }

    public String getIcon(){
        return getString("icon");
    }

    public Date getStartDate() {
        return getDate("start");
    }

    public void setDisplayDate(String displayDate){
        put("displayDate", displayDate);
    }

    public String getDisplayDate() { return getString("displayDate"); }

    public Boolean isRateable() { return getBoolean("rateable"); }
}
