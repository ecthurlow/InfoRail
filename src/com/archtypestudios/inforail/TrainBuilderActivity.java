package com.archtypestudios.inforail;

import java.util.ArrayList;
import java.util.List;

import org.lucasr.twowayview.TwoWayView;

import com.archtypestudios.inforail.adapters.TrainPartAdapter;
import com.archtypestudios.inforail.model.TrainPart;
import com.archtypestudios.inforail.repositories.Repository;

import android.os.Build;
import android.os.Bundle;
import android.R.anim;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.LinearLayout;

public class TrainBuilderActivity extends Activity {
	
	private Repository repository;
	final private Context context = this;

	private List<TrainPart> trainParts;
	
	private static ArrayList<Integer> trainPartIds;
	private static ArrayList<String> trainPartImages;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_train_builder);
		
		repository = new Repository(this);
		//Get all user won train parts
		trainParts = repository.trainParts.getAllWon();
				
		trainPartIds = getTrainPartIds();
		trainPartImages = getTrainPartImages();
		
		TwoWayView trainPartCollection = (TwoWayView)findViewById(R.id.trainPartCollection);
		
		TrainPartAdapter adapter = new TrainPartAdapter(this, R.layout.listview_item, trainParts);
		
		trainPartCollection.setAdapter(adapter);
		
        
        RelativeLayout buildingArea = (RelativeLayout) findViewById(R.id.buildingArea);
        buildingArea.setOnDragListener(new MyDragListener());
		
	}
	
	public ArrayList<Integer> getTrainPartIds() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for (TrainPart trainPart : trainParts) {
			//list.add(Integer.toString(trainPart.getId()));
			list.add(trainPart.getId());
		}
		
		//String[] trainPartIds = list.toArray(new String[list.size()]);
		
		return list;
	}
	
	public ArrayList<String> getTrainPartImages() {
		ArrayList<String> list = new ArrayList<String>();
		
		for (TrainPart trainPart : trainParts) {
			list.add("images/trainParts/" + trainPart.getTrainPartType().toString().toLowerCase() + trainPart.getId() + ".png");
		}
		return list;
	}
	
	public void goToHome(View view) {
		Intent intent = new Intent(TrainBuilderActivity.this, HomeActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.train_builder, menu);
		return true;
	}
		
	class MyDragListener implements OnDragListener {
		
		Drawable enterShape = getResources().getDrawable(R.drawable.trainbuilder_background_over);
		Drawable normalShape = getResources().getDrawable(R.drawable.trainbuilder_background);
		
        private int enterShapeId = getResources().getIdentifier("trainbuilder_background_over", "drawable", getPackageName());
        private int normalShapeId = getResources().getIdentifier("trainbuilder_background", "drawable", getPackageName());
        
        private boolean canDrop = false;
        
		@Override
		public boolean onDrag(View v, DragEvent event) {
			
			final int action = event.getAction();
			ImageView trainPart = (ImageView) event.getLocalState();
			
			switch (action) {
			
			case DragEvent.ACTION_DRAG_STARTED:
				Log.i("Drag ", "Started");
				
				trainPart.setBackgroundResource(android.R.color.transparent);
				//do nothing
				break;
			case DragEvent.ACTION_DRAG_ENTERED:
				Log.i("Drag ", "Entered");
				
				v.setBackgroundResource(enterShapeId);
				canDrop = true;
				break;
			case DragEvent.ACTION_DRAG_EXITED:
				Log.i("Drag ", "Exit");
				
				v.setBackgroundResource(normalShapeId);
				canDrop = false;
				break;
			case DragEvent.ACTION_DROP:
				//Dropped, create new ImageView with same drawable as the dropped view's ImageView
				Log.i("Drag ", "Drop");
				
				//Dropped View (ImageView of ListView)
				if (canDrop == true) {
					ViewGroup owner = (ViewGroup) trainPart.getParent();
					owner.removeView(trainPart);
					
					trainPart.setX(getPosX(trainPart.getDrawable(), event));
					trainPart.setY(getPosY(trainPart.getDrawable(), event));
					
					RelativeLayout buildingArea = (RelativeLayout) v;
					buildingArea.addView(trainPart);
					trainPart.setVisibility(View.VISIBLE);
				}
				
				
				break;
			case DragEvent.ACTION_DRAG_ENDED:
				v.setBackgroundResource(normalShapeId);
			default:
				break;
			}
			return true;
		}
		
		public float getPosX(Drawable image, DragEvent event) {
			
			BitmapDrawable bd = (BitmapDrawable) image;
			
			float moveX = event.getX() - (bd.getBitmap().getWidth())/ 2;
			return moveX;
		}
		
		public float getPosY(Drawable image, DragEvent event) {
			
			BitmapDrawable bd = (BitmapDrawable) image;
			
			float moveY = event.getY() - (bd.getBitmap().getHeight())/ 2;
			return moveY;
		}
		
	}
}
