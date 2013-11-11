package com.archtypestudios.inforail.db;

import java.io.IOException;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.archtypestudios.inforail.model.Answer;
import com.archtypestudios.inforail.model.Question;
import com.archtypestudios.inforail.model.QuestionType;
import com.archtypestudios.inforail.model.Train;
import com.archtypestudios.inforail.model.TrainInfo;
import com.archtypestudios.inforail.model.TrainPart;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	
	private static final String DATABASE_NAME = "InfoRailDB.sqlite"; // name of database file
	private static final int DATABASE_VERSION = 1; // the database version that will change when the developer updates the database (not including HCID Development)
	
	// the Database Access Object (DAO) we use to access the SimpleData table
	private Dao<Answer, Integer> answerDao = null;
	private Dao<Question, Integer> questionDao = null;
	private Dao<QuestionType, Integer> questionTypeDao = null;
	private Dao<Train, Integer> trainDao = null;
	private Dao<TrainInfo, Integer> trainInfoDao = null;
	private Dao<TrainPart, Integer> trainPartDao = null;
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
		DatabaseInitializer initializer = new DatabaseInitializer(context);
		try {
			initializer.createDatabase();
			initializer.close();
		} catch (IOException e ) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
		
		// If database does not exist, copy it from the assets
		
		try {
			Log.i(DatabaseHelper.class.getName(), "onCreate");
			
			TableUtils.createTable(connectionSource, Answer.class);
			TableUtils.createTable(connectionSource, Question.class);
			TableUtils.createTable(connectionSource, QuestionType.class);
			TableUtils.createTable(connectionSource, Train.class);
			TableUtils.createTable(connectionSource, TrainInfo.class);
			TableUtils.createTable(connectionSource, TrainPart.class);
		} catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion,
			int newVersion) {
		
		try {
			
			Log.i(DatabaseHelper.class.getName(), "onUpgrade");
			
			TableUtils.dropTable(connectionSource, Answer.class, true);
			TableUtils.dropTable(connectionSource, Question.class, true);
			TableUtils.dropTable(connectionSource, QuestionType.class, true);
			TableUtils.dropTable(connectionSource, Train.class, true);
			TableUtils.dropTable(connectionSource, TrainInfo.class, true);
			TableUtils.dropTable(connectionSource, TrainPart.class, true);
			
			onCreate(db);
			
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't drop database", e);
			throw new RuntimeException(e);
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}

	}

	public Dao<Answer, Integer> getAnswerDao() {
		
		if (null == answerDao) {
			try {
				answerDao = getDao(Answer.class);
			}catch (java.sql.SQLException e) {
				e.printStackTrace();
			}
		}
		
		return answerDao;
	}
	
	public Dao<Question, Integer> getQuestionDao() {

		if (null == questionDao) {
			try {
				questionDao = getDao(Question.class);
			}catch (java.sql.SQLException e) {
				e.printStackTrace();
			}
		}
		
		return questionDao;
	}

	public Dao<QuestionType, Integer> getQuestionTypeDao() {

		if (null == questionTypeDao) {
			try {
				questionTypeDao = getDao(QuestionType.class);
			}catch (java.sql.SQLException e) {
				e.printStackTrace();
			}
		}
		
		return questionTypeDao;
	}

	public Dao<Train, Integer> getTrainDao() {

		if (null == trainDao) {
			try {
				trainDao = getDao(Train.class);
			}catch (java.sql.SQLException e) {
				e.printStackTrace();
			}
		}
		
		return trainDao;
	}

	public Dao<TrainInfo, Integer> getTrainInfoDao() {

		if (null == trainInfoDao) {
			try {
				trainInfoDao = getDao(TrainInfo.class);
			}catch (java.sql.SQLException e) {
				e.printStackTrace();
			}
		}
		
		return trainInfoDao;
	}

	public Dao<TrainPart, Integer> getTrainPartDao() {

		if (null == trainPartDao) {
			try {
				trainPartDao = getDao(TrainPart.class);
			}catch (java.sql.SQLException e) {
				e.printStackTrace();
			}
		}
		
		return trainPartDao;
	}
	
	@Override
	public void close() {
		super.close();
		
		answerDao = null;
		questionDao = null;
		questionTypeDao = null;
		trainDao = null;
		trainInfoDao = null;
		trainPartDao = null;
	}

}
