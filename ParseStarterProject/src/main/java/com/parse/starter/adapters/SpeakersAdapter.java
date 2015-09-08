package com.parse.starter.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.starter.R;
import com.parse.starter.model.AgendaItem;
import com.parse.starter.model.Speaker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by scottrichards on 9/4/15.
 */
public class SpeakersAdapter extends ArrayAdapter<Speaker> {
    private Context mContext;
    private List<Speaker> mSpeakers;
    View mConvertView;
    HashMap<String,View> mMapTable;

    public SpeakersAdapter(Context context, List<Speaker> objects) {
        super(context, R.layout.speaker_row_item, objects);
        this.mContext = context;
        this.mSpeakers = objects;
        mMapTable = new HashMap<String,View>();
    }

    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
            convertView = mLayoutInflater.inflate(R.layout.speaker_row_item, null);
        }
        mConvertView = convertView;

        Speaker speaker= mSpeakers.get(position);

        TextView nameView = (TextView) convertView.findViewById(R.id.name);
        nameView.setText(speaker.getName());

        TextView companyView = (TextView) convertView.findViewById(R.id.company);
        companyView.setText(speaker.getCompany());

        TextView titleView = (TextView) convertView.findViewById(R.id.title);
        titleView.setText(speaker.getTitle());

        TextView bioView = (TextView) convertView.findViewById(R.id.bio);
        bioView.setText(speaker.getBio());

        final ParseFile imageFile = (ParseFile)speaker.get("headshot");

        if (imageFile != null) {
            mMapTable.put(imageFile.getUrl(),mConvertView); // save the view by url in hash map table so we can retrieve it after asynchronous callback
            imageFile.getDataInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] data, ParseException e) {
                    Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                    View rowView = mMapTable.get(imageFile.getUrl());
                    if (rowView != null) {
                        ImageView headshotView = (ImageView) rowView.findViewById(R.id.headshot);
                        // Set the Bitmap into the ImageView
                        headshotView.setImageBitmap(bmp);
                    }
                }
            });
        } else {
            ImageView headshotView = (ImageView) mConvertView.findViewById(R.id.headshot);
            headshotView.setImageBitmap(null);  // clear out the image
        }

        return convertView;
    }
}
