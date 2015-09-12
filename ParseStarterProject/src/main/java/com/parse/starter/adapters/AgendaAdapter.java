package com.parse.starter.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.starter.R;
import com.parse.starter.model.AgendaItem;

import java.util.List;

import bolts.Task;

/**
 * Created by scottrichards on 9/4/15.
 */
public class AgendaAdapter extends ArrayAdapter<AgendaItem> {

    private Context mContext;
    private List<AgendaItem> mAgendaItems;

    public AgendaAdapter(Context context, List<AgendaItem> objects) {
        super(context, R.layout.agenda_item, objects);
        this.mContext = context;
        this.mAgendaItems = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {

        if(convertView == null){
            LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
            convertView = mLayoutInflater.inflate(R.layout.agenda_item, parent, false);
        }

        if (position < mAgendaItems.size()) {
            Log.d("AgendaAdapter","getting item " + position + " of " + mAgendaItems.size());
            AgendaItem agendaItem = mAgendaItems.get(position);

            TextView sessionNameView = (TextView) convertView.findViewById(R.id.textView5);
            sessionNameView.setText(agendaItem.getSessionName());

        }


//
//        TextView displayTimeView = (TextView) convertView.findViewById(R.id.displayTime);
//        displayTimeView.setText(agendaItem.getDisplayTime());
//
//        TextView locationView = (TextView) convertView.findViewById(R.id.locationPlace);
//        locationView.setText(agendaItem.getLocation());
//
//        String iconName = agendaItem.getIcon();
//        ImageView iconView = (ImageView) convertView.findViewById(R.id.iconView);
//        if (iconName !=null && iconName.length() > 0) {
////            Log.d("agendaItem", "Icon Name: " + iconName);
//            if (iconName.equals("coffee")) {
//                iconView.setImageResource(R.drawable.coffee_75);
//            } else if (iconName.equals("cocktail")) {
//                iconView.setImageResource(R.drawable.beer_50);
//            } else if (iconName.equals("trophy")) {
//                iconView.setImageResource(R.drawable.trophy_50);
//            } else if (iconName.equals("food")) {
//                iconView.setImageResource(R.drawable.fork_75);
//            } else if (iconName.equals("key")) {
//                iconView.setImageResource(R.drawable.key_filled_50);
//            } else if (iconName.equals("expert")) {
//                iconView.setImageResource(R.drawable.collaboration_50);
//            }
//        } else {
//            iconView.setImageBitmap(null);
//        }
        return convertView;
    }
}
