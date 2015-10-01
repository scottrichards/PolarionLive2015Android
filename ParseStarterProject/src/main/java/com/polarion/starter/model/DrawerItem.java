package com.polarion.starter.model;

/**
 * Created by scottrichards on 9/9/15.
 */
public class DrawerItem extends Object {
    public String title;
    public int resourceId;

    public DrawerItem(String title, int resourceId) {
        this.title = title;
        this.resourceId = resourceId;
    }
}
