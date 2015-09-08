package com.parse.starter.model;

import android.graphics.Bitmap;
import android.view.View;

/**
 * Created by scottrichards on 9/8/15.
 */
public class ImageCache extends Object {
    public Bitmap bitmap;
    public View convertView;
    public String url;

    public ImageCache(Bitmap bitmap,View convertView,String url) {
        this.bitmap = bitmap;
        this.convertView = convertView;
        this.url = url;
    }
}
