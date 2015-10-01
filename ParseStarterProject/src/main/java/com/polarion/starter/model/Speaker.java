package com.polarion.starter.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
/**
 * Created by scottrichards on 9/4/15.
 */

@ParseClassName("Speakers")
public class Speaker extends ParseObject {
    public Speaker() {
    }

    public String getName(){
        return getString("name");
    }

    public void setName(String name){
        put("name", name);
    }

    public String getCompany(){
        return getString("company");
    }

    public void setCompany(String company){
        put("company", company);
    }

    public String getTitle(){
        return getString("title");
    }

    public void setTitle(String title){
        put("title", title);
    }

    public String getBio(){
        return getString("bio");
    }

    public void setBio(String bio){
        put("bio", bio);
    }

    @Override
    public String toString() {
        return getName();
    }
}
