package com.archtypestudios.inforail;


import java.util.List;

import com.archtypestudios.inforail.model.TrainInfo;
import com.archtypestudios.inforail.repositories.Repository;

import android.os.Bundle;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SelectedTrainActivity extends Activity {
	
	Repository repository;
	
	int id;
	String name;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.activity_selected_train);
		
		repository = new Repository(this);
		
		//Get the intent recieved from the home activity
		Bundle bundle = new Bundle();
		bundle = getIntent().getExtras();
		id = bundle.getInt("id");
		name = bundle.getString("name");
		final List<TrainInfo> trainInfos = repository.trainInfo.getByTrain(id);
		
		//Get Elements
		TextView trainNameTextView = (TextView) findViewById(R.id.selected_train_name);
		RelativeLayout content = (RelativeLayout) findViewById(R.id.selectedActivity_Content);
		
		//Set Element Values
		trainNameTextView.setText(name);
		
		String drawableName = "train" + id;
        
        int drawableId = getResources().getIdentifier(drawableName, "drawable", getPackageName());
        content.setBackgroundResource(drawableId);
        
        for (TrainInfo trainInfo : trainInfos) {
			ImageView infoIcon = new ImageView(this);
			infoIcon.setId(trainInfo.getId());
			infoIcon.setImageResource(R.drawable.ic_launcher);
			
			infoIcon.getLayoutParams().width = LayoutParams.WRAP_CONTENT;
			infoIcon.getLayoutParams().height = LayoutParams.WRAP_CONTENT;
			
			infoIcon.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					//set train info in a text view
					TextView trainNameTextView = (TextView) findViewById(R.id.selected_train_name);
					trainNameTextView.setText(trainInfos.get(v.getId()).getTextStringId());
				}
			});
		}
		
	}
	
	public void loadTrainInfo(List<TrainInfo> trainInfos) { 
		
		
	}
	
	public void goToHome(View view) {
		Intent intent = new Intent(SelectedTrainActivity.this, HomeActivity.class);
		startActivity(intent);
	}
	
	public void goToQuiz(View view) {
		Intent intent = new Intent(SelectedTrainActivity.this, QuizActivity.class);
		intent.putExtra("id", id);
		intent.putExtra("name", name);
		startActivity(intent);
	}
	
	public void goToTrainBuilder(View view) {
		Intent intent = new Intent(SelectedTrainActivity.this, TrainBuilderActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.selected_train, menu);
		return true;
	}

}
