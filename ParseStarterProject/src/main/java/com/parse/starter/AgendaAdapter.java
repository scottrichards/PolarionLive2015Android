package com.parse.starter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
        super(context, R.layout.agenda_row_item, objects);
        this.mContext = context;
        this.mAgendaItems = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
            convertView = mLayoutInflater.inflate(R.layout.agenda_row_item, null);
        }

        AgendaItem agendaItem = mAgendaItems.get(position);

        TextView sessionNameView = (TextView) convertView.findViewById(R.id.sessionName);
        sessionNameView.setText(agendaItem.getSessionName());

        TextView displayTimeView = (TextView) convertView.findViewById(R.id.displayTime);
        displayTimeView.setText(agendaItem.getDisplayTime());

        TextView locationView = (TextView) convertView.findViewById(R.id.locationPlace);
        locationView.setText(agendaItem.getLocation());

        return convertView;
    }
}
