package com.archtypestudios.inforail;

import java.util.ArrayList;
import java.util.List;

import org.lucasr.twowayview.TwoWayView;

import com.archtypestudios.inforail.adapters.TrainPartAdapter;
import com.archtypestudios.inforail.model.TrainPart;
import com.archtypestudios.inforail.repositories.Repository;

import android.os.Bundle;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;

public class TrainBuilderActivity extends Activity {
	
	private Repository repository;

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
		
		 //register onClickListener to handle click events on each item
        trainPartCollection.setOnItemLongClickListener(new OnItemLongClickListener() {
        	//argument position gives the index of item which is clicked
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View v,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				DragShadowBuilder dragshadow = new DragShadowBuilder(v);
        		ClipData data = ClipData.newPlainText("", "");
        		
        		v.startDrag(data, dragshadow, v, 0);
        		return true;
			}
		});
		
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
	
	OnDragListener DropListener = new View.OnDragListener() {

	    @Override
	    public boolean onDrag(View v, DragEvent event) {
	        // TODO Auto-generated method stub

	        int dragEvent = event.getAction();

	        switch (dragEvent) {
	        case DragEvent.ACTION_DRAG_ENTERED:
	            Log.i("Drag ", "Entered");
	            break;
	        case DragEvent.ACTION_DRAG_EXITED:
	            Log.i("Drag ", "Exit");
	            break;
	        case DragEvent.ACTION_DROP:
	            Log.i("Drag ", "Drop");

	            //Stuff here
	            break;
	        default:
	            break;
	        }

	        return true;
	    }
	};

}
