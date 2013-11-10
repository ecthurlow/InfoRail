package com.archtypestudios.inforail;

import java.util.ArrayList;
import java.util.List;

import com.archtypestudios.inforail.R;
import com.archtypestudios.inforail.model.Train;
import com.archtypestudios.inforail.repositories.Repository;

import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HomeActivity extends Activity {
	
	Repository repository;
	
	//ListView trainListView;
	
	SimpleCursorAdapter mAdapter;
	
	ArrayList<String> trainNamesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        
        repository = new Repository(this);
        
        ListView trainListView = (ListView)findViewById(R.id.train_list_view);
        
        //A list of all train objects
        final List<Train> trains = repository.trains.getAll();
        
        trainNamesList = getTrainNames();
        
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, trainNamesList);
        //Set The AdapterView
        trainListView.setAdapter(arrayAdapter);
        
        //register onClickListener to handle click events on each item
        trainListView.setOnItemClickListener(new OnItemClickListener() {
        	//argument position gives the index of item which is clicked
        	public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
        		//String selectedTrain = trainNamesList.get(position);
        		//Toast.makeText(getApplicationContext(), "Train Selected:" + selectedTrain, Toast.LENGTH_LONG).show();
        		
        		Intent intent = new Intent(HomeActivity.this, SelectedTrainActivity.class);
        		intent.putExtra("id", trains.get(position).getId());
        		intent.putExtra("name", trains.get(position).getNameStringId());
        		startActivity(intent);
        	}
		});
        
        
    }
    
    @Override
    protected void onStart() {
    	super.onStart();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
    
    private ArrayList<String> getTrainNames() {
    	List<Train> trains = repository.trains.getAll();
    	
    	ArrayList<String> names = new ArrayList<String>();
    	
    	for (Train train : trains) {
    		names.add(train.getNameStringId());
    	}
    	
    	return names;
    }
    
}
