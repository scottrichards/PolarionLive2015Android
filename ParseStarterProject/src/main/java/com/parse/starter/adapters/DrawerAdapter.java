package com.parse.starter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.starter.R;
import com.parse.starter.model.DrawerItem;

/**
 * Created by scottrichards on 9/9/15.
 */
public class DrawerAdapter extends ArrayAdapter<DrawerItem> {
    private Context mContext;
    private String[] mDrawerTitles  = {
            "Home",
            "Agenda",
            "Speakers",
            "Partners",
            "Map",
            "Raffle",
            "Log In",
            "Social"};

    private int[] mDrawerIcons  = {
            R.drawable.iconhome,
            R.drawable.agenda,
            R.drawable.speaker,
            R.drawable.partners,
            R.drawable.map,
            R.drawable.raffle,
            R.drawable.iconlogin,
            R.drawable.social};

    public DrawerAdapter(Context context) {
        super(context, R.layout.drawer_row_item);
        mContext = context;
        initDrawerItems();
    }

    private void initDrawerItems()
    {

        for (int i=0; i < mDrawerTitles.length;i++) {
            if (i < mDrawerIcons.length) {
                DrawerItem drawerItem = new DrawerItem(mDrawerTitles[i],mDrawerIcons[i]);
                this.add(drawerItem);
            }
        }
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView == null){
            LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
            convertView = mLayoutInflater.inflate(R.layout.drawer_row_item, null);
        }


        DrawerItem drawerItem = this.getItem(position);

        if (drawerItem != null) {
            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
            imageView.setImageResource(drawerItem.resourceId);

            TextView titleView = (TextView) convertView.findViewById(R.id.textView);
            titleView.setText(drawerItem.title);
        }

        return convertView;
    }
}
