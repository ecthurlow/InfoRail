package com.archtypestudios.inforail;

import android.R.layout;
import android.os.Bundle;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class SelectedTrainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selected_train);
		
		//Get the intent recieved from the home activity
		Bundle bundle = new Bundle();
		bundle = getIntent().getExtras();
		int id  = bundle.getInt("id");
		String stringName = "train_name_" + id;
		
		TextView trainNameTextView = (TextView) findViewById(R.id.selected_train_name);
		
		int stringNameId = getResources().getIdentifier(stringName, "string", this.getPackageName());
		
		trainNameTextView.setText(this.getString(stringNameId));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.selected_train, menu);
		return true;
	}

}
