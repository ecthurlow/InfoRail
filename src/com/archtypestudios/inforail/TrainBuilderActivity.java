package com.archtypestudios.inforail;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.lucasr.twowayview.TwoWayView;

import com.archtypestudios.inforail.adapters.TrainPartAdapter;
import com.archtypestudios.inforail.model.TrainPart;
import com.archtypestudios.inforail.repositories.Repository;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;

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
		
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, resource)
		
		/*
		ArrayAdapter<Integer> ids = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, trainPartIds);
		TwoWayView trainPartCollection = (TwoWayView)findViewById(R.id.trainPartCollection);
		trainPartCollection.setAdapter(ids);
		*/
	}
		
	public Bitmap getBitmapFromAsset(String strName){
        AssetManager assetManager = getAssets();
        InputStream istr = null;
        try {
            istr = assetManager.open(strName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(istr);
        return bitmap;
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

}
