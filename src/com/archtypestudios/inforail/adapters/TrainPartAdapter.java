package com.archtypestudios.inforail.adapters;

import java.util.List;

import com.archtypestudios.inforail.R;
import com.archtypestudios.inforail.model.TrainPart;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
        
    	String drawableName = trainPart.getTrainPartType().toString().toLowerCase() + trainPart.getId();
        int drawableId = context.getResources().getIdentifier(drawableName, "drawable", context.getPackageName());
        
        Drawable scaledDrawable = getScaledDrawable(drawableId, 200);
        
        holder.imgIcon.setImageDrawable(scaledDrawable);
        
        return row;
    }
    
	public Drawable getScaledDrawable(int drawableId, int boundBoxInDp) {
    	
		BitmapDrawable bd = (BitmapDrawable) context.getResources().getDrawable(drawableId);
		
    	// Get current dimensions
        double imageWidth = bd.getBitmap().getWidth();
        double imageHeight = bd.getBitmap().getHeight();
        
        double ratio = ((double)boundBoxInDp) /imageWidth;
        int newImageHeight = (int) (imageHeight * ratio);

        Bitmap bMap = BitmapFactory.decodeResource(context.getResources(), drawableId);
        Drawable drawable = new BitmapDrawable(context.getResources(), getResizedBitmap(bMap, newImageHeight, boundBoxInDp));
        
        return drawable;
    }
	
	public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
		
		int width = bm.getWidth();
		int height = bm.getHeight();
		
		float scaleWidth = ((float)  newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		
		Matrix matrix = new Matrix();
		
		matrix.postScale(scaleWidth, scaleHeight);
		
		Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
		
		return resizedBitmap;
	}
    
    static class TrainPartHolder
    {
        ImageView imgIcon;
        TextView txtTitle;
    }
}
