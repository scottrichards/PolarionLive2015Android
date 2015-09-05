package com.parse.starter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.starter.R;
import com.parse.starter.model.AgendaItem;
import com.parse.starter.model.Speaker;

import java.util.List;

/**
 * Created by scottrichards on 9/4/15.
 */
public class SpeakersAdapter extends ArrayAdapter<Speaker> {
    private Context mContext;
    private List<Speaker> mSpeakers;

    public SpeakersAdapter(Context context, List<Speaker> objects) {
        super(context, R.layout.speaker_row_item, objects);
        this.mContext = context;
        this.mSpeakers = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
            convertView = mLayoutInflater.inflate(R.layout.speaker_row_item, null);
        }

        Speaker speaker= mSpeakers.get(position);

        TextView nameView = (TextView) convertView.findViewById(R.id.name);
        nameView.setText(speaker.getName());

        TextView companyView = (TextView) convertView.findViewById(R.id.company);
        companyView.setText(speaker.getCompany());

        TextView titleView = (TextView) convertView.findViewById(R.id.title);
        titleView.setText(speaker.getTitle());

        TextView bioView = (TextView) convertView.findViewById(R.id.bio);
        bioView.setText(speaker.getBio());

        return convertView;
    }
}
