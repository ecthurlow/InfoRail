package com.archtypestudios.inforail;


import java.util.List;

import com.archtypestudios.inforail.model.TrainInfo;
import com.archtypestudios.inforail.repositories.Repository;
import com.archtypestudios.inforail.themes.InfoRailActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SelectedTrainActivity extends Activity {
	
	protected TextView title;
	protected ImageView icon;
	
	protected Repository repository;
	
	protected int id;
	protected String name;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		
		setContentView(R.layout.activity_selected_train);
		
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,    R.layout.window_title);
		
		title = (TextView) findViewById(R.id.title);
        icon  = (ImageView) findViewById(R.id.icon);
        icon.setImageResource(R.drawable.ic_launcher);
		
		repository = new Repository(this);
		
		//Get the intent recieved from the home activity
		Bundle bundle = new Bundle();
		bundle = getIntent().getExtras();
		id = bundle.getInt("id");
		name = bundle.getString("name");
		final List<TrainInfo> trainInfos = repository.trainInfo.getByTrain(id);
		
		
		this.title.setText(name);
		
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
			
			infoIcon.setLayoutParams(new ViewGroup.LayoutParams(40, 40));
			
			
			infoIcon.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					//set train info in a text view
					TextView trainNameTextView = (TextView) findViewById(R.id.selected_train_name);
					int trainInfoIndex = v.getId()-1;
					trainNameTextView.setText(trainInfos.get(trainInfoIndex).getTextStringId());
				}
			});
			
			content.addView(infoIcon);
		}
		
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
