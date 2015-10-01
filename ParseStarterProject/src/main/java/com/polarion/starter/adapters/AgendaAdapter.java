package com.polarion.starter.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.polarion.starter.model.AgendaItem;

import java.util.List;

/**
 * Created by scottrichards on 9/4/15.
 */
public class AgendaAdapter extends ArrayAdapter<AgendaItem> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;
    private static final int TYPE_MAX_COUNT = TYPE_SEPARATOR + 1;

    private Context mContext;
    private List<AgendaItem> mAgendaItems;

    public AgendaAdapter(Context context, List<AgendaItem> objects) {
        super(context, com.polarion.starter.R.layout.agenda_row_item, objects);
        this.mContext = context;
        this.mAgendaItems = objects;
    }

    public AgendaItem getItem(int position)
    {
        if (position < mAgendaItems.size())
            return mAgendaItems.get(position);
        else
            return null;
    }

    public void addSeparatorItem(AgendaItem separatorItem,String displayDate)
    {
        separatorItem.setDisplayDate(displayDate);
        separatorItem.itemType = TYPE_SEPARATOR;
        this.add(separatorItem);
    }

    public void addItem(AgendaItem item)
    {
        item.itemType = TYPE_ITEM;
        this.add(item);
    }

    @Override
    public int getItemViewType(int position) {
        AgendaItem agendaItem = mAgendaItems.get(position);
        return agendaItem.itemType;
//        return mAgendaItems(position) ? TYPE_SEPARATOR : TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_MAX_COUNT;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {

        if (position < mAgendaItems.size()) {
            AgendaItem agendaItem = mAgendaItems.get(position);
 //           ViewHolder holder = null;
            if(convertView == null){
                LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
                switch (agendaItem.itemType) {
                    case TYPE_ITEM :
                        convertView = mLayoutInflater.inflate(com.polarion.starter.R.layout.agenda_row_item, parent, false);
                        break;
                    case TYPE_SEPARATOR :
                        convertView = mLayoutInflater.inflate(com.polarion.starter.R.layout.agenda_separator_item, parent, false);
                        break;
                }
            }
            // it is a an ITEM TYPE
            if (agendaItem.itemType == TYPE_ITEM) {
                Log.d("AgendaAdapter", "getting item " + position + " of " + mAgendaItems.size());


                TextView sessionNameView = (TextView) convertView.findViewById(com.polarion.starter.R.id.sessionName);
                sessionNameView.setText(agendaItem.getSessionName());

                TextView displayTimeView = (TextView) convertView.findViewById(com.polarion.starter.R.id.displayTime);
                displayTimeView.setText(agendaItem.getDisplayTime());

                TextView locationView = (TextView) convertView.findViewById(com.polarion.starter.R.id.locationPlace);
                locationView.setText(agendaItem.getLocation());

                String iconName = agendaItem.getIcon();
                ImageView iconView = (ImageView) convertView.findViewById(com.polarion.starter.R.id.iconView);
                if (iconName != null && iconName.length() > 0) {
                    //            Log.d("agendaItem", "Icon Name: " + iconName);
                    if (iconName.equals("coffee")) {
                        iconView.setImageResource(com.polarion.starter.R.drawable.coffee_75);
                    } else if (iconName.equals("cocktail")) {
                        iconView.setImageResource(com.polarion.starter.R.drawable.beer_50);
                    } else if (iconName.equals("trophy")) {
                        iconView.setImageResource(com.polarion.starter.R.drawable.trophy_50);
                    } else if (iconName.equals("food")) {
                        iconView.setImageResource(com.polarion.starter.R.drawable.fork_75);
                    } else if (iconName.equals("key")) {
                        iconView.setImageResource(com.polarion.starter.R.drawable.key_filled_50);
                    } else if (iconName.equals("expert")) {
                        iconView.setImageResource(com.polarion.starter.R.drawable.collaboration_50);
                    }
                } else {
                    iconView.setImageBitmap(null);
                }
            }
            else {    // It is a separator type
                TextView dateView = (TextView) convertView.findViewById(com.polarion.starter.R.id.date);
                dateView.setText(agendaItem.getDisplayDate());
            }
        }

        return convertView;
    }
}
