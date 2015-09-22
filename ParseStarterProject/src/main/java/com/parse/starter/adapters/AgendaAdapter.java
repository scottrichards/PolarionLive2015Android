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

import java.util.Date;
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
        super(context, R.layout.agenda_row_item, objects);
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

    public void addSeparatorItem(Date currentDate)
    {
        AgendaItem separatorItem = new AgendaItem();
        separatorItem.date = currentDate;
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
        return mAgendaItems.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_MAX_COUNT;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {

        if(convertView == null){
            LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
            convertView = mLayoutInflater.inflate(R.layout.agenda_row_item, parent, false);
        }

        if (position < mAgendaItems.size()) {
            Log.d("AgendaAdapter","getting item " + position + " of " + mAgendaItems.size());
            AgendaItem agendaItem = mAgendaItems.get(position);

            TextView sessionNameView = (TextView) convertView.findViewById(R.id.time);
            sessionNameView.setText(agendaItem.getSessionName());

            TextView displayTimeView = (TextView) convertView.findViewById(R.id.displayTime);
            displayTimeView.setText(agendaItem.getDisplayTime());

            TextView locationView = (TextView) convertView.findViewById(R.id.locationPlace);
            locationView.setText(agendaItem.getLocation());

            String iconName = agendaItem.getIcon();
            ImageView iconView = (ImageView) convertView.findViewById(R.id.iconView);
            if (iconName !=null && iconName.length() > 0) {
//            Log.d("agendaItem", "Icon Name: " + iconName);
                if (iconName.equals("coffee")) {
                    iconView.setImageResource(R.drawable.coffee_75);
                } else if (iconName.equals("cocktail")) {
                    iconView.setImageResource(R.drawable.beer_50);
                } else if (iconName.equals("trophy")) {
                    iconView.setImageResource(R.drawable.trophy_50);
                } else if (iconName.equals("food")) {
                    iconView.setImageResource(R.drawable.fork_75);
                } else if (iconName.equals("key")) {
                    iconView.setImageResource(R.drawable.key_filled_50);
                } else if (iconName.equals("expert")) {
                    iconView.setImageResource(R.drawable.collaboration_50);
                }
            } else {
                iconView.setImageBitmap(null);
            }
        }

        return convertView;
    }
}
