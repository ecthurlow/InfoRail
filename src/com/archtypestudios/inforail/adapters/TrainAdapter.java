package com.archtypestudios.inforail.adapters;

import java.util.List;

import com.archtypestudios.inforail.R;
import com.archtypestudios.inforail.model.Train;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class TrainAdapter extends ArrayAdapter<Train> {
	
	Context context; 
    int layoutResourceId;    
    List<Train> data = null;
    
    public TrainAdapter(Context context, int layoutResourceId, List<Train> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        TrainHolder holder = null;
        
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            
            holder = new TrainHolder();
            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            
            row.setTag(holder);
        }
        else
        {
            holder = (TrainHolder)row.getTag();
        }
        
        Train train = data.get(position);
        
        String drawableName = "train_icon" + train.getId();
        
        int drawableId = context.getResources().getIdentifier(drawableName, "drawable", context.getPackageName());
        holder.imgIcon.setImageResource(drawableId);
        
        row.setId(train.getId());
        
        return row;
    }
    
    static class TrainHolder
    {
        ImageView imgIcon;
    }
}
