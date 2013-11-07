package com.archtypestudios.inforail.repositories;

import android.content.Context;

import com.archtypestudios.inforail.db.DatabaseHelper;
import com.archtypestudios.inforail.db.DatabaseManager;

public class Repository {

	DatabaseHelper db;
	
	public TrainRepository trains;
	
	public Repository(Context context) {
		
		DatabaseManager manager = new DatabaseManager();
		db = manager.getHelper(context);
		
		trains = new TrainRepository(db);
	}
}
