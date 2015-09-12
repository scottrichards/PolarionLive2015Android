package com.parse.starter.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
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
import com.parse.starter.model.ImageCache;
import com.parse.starter.model.Speaker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by scottrichards on 9/4/15.
 */
public class SpeakersAdapter extends ArrayAdapter<Speaker> {
    private Context mContext;
    public List<Speaker> mSpeakers;
    View mConvertView;
    HashMap<String,ImageCache> mMapTable;

    public SpeakersAdapter(Context context, List<Speaker> objects) {
        super(context, R.layout.speaker_row_item, objects);
        this.mContext = context;
        this.mSpeakers = objects;
        mMapTable = new HashMap<String,ImageCache>();
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
//            ImageCache imageCache = mMapTable.get(imageFile.getUrl());
//            Log.d("SpeakersAdapter", "Loading image for: " + speaker.getName() + " = " + imageFile.getUrl());
//            if (imageCache == null /*|| imageCache.bitmap == null*/) {
//                Log.d("SpeakersAdapter", "imageCache == null || imageCache.bitmap == null " + speaker.getName());
//                if (imageCache == null) {   // if it has not been entered in hash table then add it
                    ImageView headshotView = (ImageView) mConvertView.findViewById(R.id.headshot);
                    headshotView.setImageBitmap(null);  // clear out the image
                    ImageCache imageCache = new ImageCache(mConvertView, imageFile.getUrl());
                    mMapTable.put(imageFile.getUrl(), imageCache); // save the view by url in hash map table so we can retrieve it after asynchronous callback
//                }
                imageFile.getDataInBackground(new GetDataCallback() {
                    @Override
                    public void done(byte[] data, ParseException e) {
                        ImageCache localImageCache = mMapTable.get(imageFile.getUrl());
                        if (localImageCache != null) {
                            localImageCache.bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                            ImageView headshotView = (ImageView) localImageCache.convertView.findViewById(R.id.headshot);
                            // Set the Bitmap into the ImageView
                            headshotView.setImageBitmap(localImageCache.bitmap);
                        }
                    }
                });
//            }
//            else {
//                ImageCache localImageCache = mMapTable.get(imageFile.getUrl());
//                if (localImageCache != null) {
//                    ImageView headshotView = (ImageView) convertView.findViewById(R.id.headshot);
//                    // Set the Bitmap into the ImageView
//                    headshotView.setImageBitmap(localImageCache.bitmap);
//                }
//            }
        } else {
            ImageView headshotView = (ImageView) mConvertView.findViewById(R.id.headshot);
            headshotView.setImageBitmap(null);  // clear out the image
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return mSpeakers.size();
    }
}
