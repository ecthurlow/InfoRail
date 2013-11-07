package com.archtypestudios.inforail;

import java.util.ArrayList;
import java.util.List;

import com.archtypestudios.inforail.constants.Constants;
import com.archtypestudios.inforail.R;
import com.archtypestudios.inforail.model.Train;
import com.archtypestudios.inforail.repositories.Repository;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HomeActivity extends Activity {
	
	Repository repository;
	
	ListView trainListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        repository = new Repository(this);
        
        ViewGroup contentView = (ViewGroup) getLayoutInflater().inflate(R.layout.activity_home, null);
        
        trainListView = (ListView) contentView.findViewById(R.id.train_list_view);
        
        
        setContentView(R.layout.activity_home);
    }
    
    @Override
    protected void onStart() {
    	super.onStart();
    	setupTrainListView(trainListView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
    
    private void setupTrainListView(ListView tlv) {
    	
    	final List<Train> trains = repository.trains.getAll();
    	
    	List<String> names = new ArrayList<String>();
    	for (Train train : trains) {
    		names.add(train.getEnglishName());
    	}
    	
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
    	tlv.setAdapter(adapter);
    	
    	final Activity activity = this;
    	tlv.setOnItemClickListener(new OnItemClickListener() {
    		
    		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    			Train train = trains.get(position);
    			Intent intent = new Intent(activity, SelectedTrainActivity.class);
    			intent.putExtra(Constants.keyTrainId, train.getId());
    			startActivity(intent);
    		}
		});
    }
    
}
