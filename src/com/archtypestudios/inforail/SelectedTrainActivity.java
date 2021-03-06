package com.archtypestudios.inforail;


import java.util.List;

import com.archtypestudios.inforail.model.TrainInfo;
import com.archtypestudios.inforail.repositories.Repository;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SelectedTrainActivity extends Activity {
	
	protected Repository repository;
	
	protected int id;
	protected String name;
	protected List<TrainInfo> trainInfos;
	
	protected RelativeLayout content;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_selected_train);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.header);
        
        TextView subtitle = (TextView)findViewById(R.id.subtitle);
		
		repository = new Repository(this);
		
		//Get the intent recieved from the home activity
		Bundle bundle = new Bundle();
		bundle = getIntent().getExtras();
		id = bundle.getInt("id");
		name = bundle.getString("name");
		
		subtitle.setText(name);
		
		trainInfos = repository.trainInfo.getByTrain(id);
		
		//Get Elements
		content = (RelativeLayout) findViewById(R.id.selectedActivity_Content);
		
		
		//Set Element Values
		
		String drawableName = "train" + id;
        
        int drawableId = getResources().getIdentifier(drawableName, "drawable", getPackageName());
        content.setBackgroundResource(drawableId);
        
        
		
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		
		if(hasFocus) {
			for (TrainInfo trainInfo : trainInfos) {
				ImageView infoIcon = new ImageView(this);
				infoIcon.setId(trainInfo.getId());
				infoIcon.setImageResource(R.drawable.fact_normal);
				
				RelativeLayout.LayoutParams params = getFactIconParams(80, trainInfo.getxPosition(), trainInfo.getyPosition());
				
				infoIcon.setLayoutParams(params);
				
				content.addView(infoIcon);
				
				infoIcon.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						int trainInfoId = v.getId();
						
						ImageView factIcon = (ImageView) v;
						factIcon.setBackgroundResource(R.drawable.fact_tap);
						
						AlertDialog.Builder builder = new AlertDialog.Builder(SelectedTrainActivity.this);
						
						builder.setMessage((repository.trainInfo.getById(trainInfoId)).getTextStringId()).setNegativeButton("OK", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								dialog.cancel();
							}
						});
						
						AlertDialog alert = builder.create();
						
						alert.show();
						
					}
				});
				
				
			}
		}
	}
	
	public RelativeLayout.LayoutParams getFactIconParams (int size, float pX, float pY) {
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(size, size);
		
		int leftMargin = (int) (content.getWidth() * pX);
		int topMargin = (int) (content.getHeight() * pY);
		
		params.setMargins(leftMargin, topMargin, 0, 0);
		
		return params;
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
