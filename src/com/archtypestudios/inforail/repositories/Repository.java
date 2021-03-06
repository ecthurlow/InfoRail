package com.archtypestudios.inforail.repositories;

import android.content.Context;

import com.archtypestudios.inforail.db.DatabaseHelper;
import com.archtypestudios.inforail.db.DatabaseManager;

public class Repository {

	DatabaseHelper db;
	
	public AnswerRepository answers;
	public QuestionRepository questions;
	public TrainRepository trains;
	public TrainPartRepository trainParts;
	public TrainInfoRepository trainInfo;
	
	public Repository(Context context) {
		
		DatabaseManager manager = new DatabaseManager();
		db = manager.getHelper(context);
		
		answers = new AnswerRepository(db);
		questions = new QuestionRepository(db);
		trains = new TrainRepository(db);
		trainInfo = new TrainInfoRepository(db);
		trainParts = new TrainPartRepository(db);
	}
}
