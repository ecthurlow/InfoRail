package com.archtypestudios.inforail.adapters;

import java.util.List;
import java.util.Locale;

import com.archtypestudios.inforail.R;
import com.archtypestudios.inforail.model.TrainPart;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.View.DragShadowBuilder;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TrainPartAdapter extends ArrayAdapter<TrainPart> {
	
	static Context context; 
    int layoutResourceId;    
    List<TrainPart> data = null;
    
    public TrainPartAdapter(Context context, int layoutResourceId, List<TrainPart> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        TrainPartAdapter.context = context;
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
        
        //get train part from list
        TrainPart trainPart = data.get(position);
        //set text view to train part id TODO:Remove this?
        holder.txtTitle.setText(Integer.toString(trainPart.getId()));
        
        //get train part image id from drawables by name
    	String drawableName = trainPart.getTrainPartType().toString().toLowerCase(Locale.getDefault()) + trainPart.getId();
        int drawableId = context.getResources().getIdentifier(drawableName, "drawable", context.getPackageName());
        
        //set image drawable
        holder.imgIcon.setImageResource(drawableId);
        
        //set startDrag event listener
        holder.imgIcon.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				DragShadowBuilder dragshadow = new DragShadowBuilder(v);
        		ClipData data = ClipData.newPlainText("", "");
        		
        		
        		v.startDrag(data, dragshadow, v, 0);
        		return true;
			}	
		});
        
        return row;
    }
    
    static class TrainPartHolder
    {
        ImageView imgIcon;
        TextView txtTitle;
    }
}
