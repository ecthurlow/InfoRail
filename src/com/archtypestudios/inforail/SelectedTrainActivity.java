package com.archtypestudios.inforail;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class SelectedTrainActivity extends Activity {
	
	int id;
	String name;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.activity_selected_train);
		
		//Get the intent recieved from the home activity
		Bundle bundle = new Bundle();
		bundle = getIntent().getExtras();
		id = bundle.getInt("id");
		name = bundle.getString("name");
		
		
		//Set the train name in the textView
		TextView trainNameTextView = (TextView) findViewById(R.id.selected_train_name);
		
		trainNameTextView.setText(name);
		
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
