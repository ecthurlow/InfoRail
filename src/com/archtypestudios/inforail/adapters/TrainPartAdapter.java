package com.archtypestudios.inforail.adapters;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.archtypestudios.inforail.R;
import com.archtypestudios.inforail.model.TrainPart;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TrainPartAdapter extends ArrayAdapter<TrainPart> {
	
	Context context; 
    int layoutResourceId;    
    List<TrainPart> data = null;
    
    public TrainPartAdapter(Context context, int layoutResourceId, List<TrainPart> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        TrainPartHolder holder = null;
        
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            
            holder = new TrainPartHolder();
            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
            
            row.setTag(holder);
        }
        else
        {
            holder = (TrainPartHolder)row.getTag();
        }
        
        TrainPart trainPart = data.get(position);
        holder.txtTitle.setText(Integer.toString(trainPart.getId()));
        //holder.imgIcon.setImageResource(trainPart.);
        
        Bitmap bitmap = getBitmapFromAsset("images/trainParts/" + trainPart.getTrainPartType().toString().toLowerCase() + trainPart.getId() + ".png");
        holder.imgIcon.setImageBitmap(bitmap);
        
        return row;
    }
    
    public Bitmap getBitmapFromAsset(String strName){
        AssetManager assetManager = context.getAssets();
        InputStream istr = null;
        try {
            istr = assetManager.open(strName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(istr);
        return bitmap;
    }
    
    static class TrainPartHolder
    {
        ImageView imgIcon;
        TextView txtTitle;
    }
}
