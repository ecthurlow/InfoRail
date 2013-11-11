package com.archtypestudios.inforail.repositories;

import java.util.List;

import android.database.SQLException;

import com.archtypestudios.inforail.db.DatabaseHelper;
import com.archtypestudios.inforail.model.Question;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

public class QuestionRepository {

Dao<Question, Integer> questionDao;
	
	public QuestionRepository(DatabaseHelper db) {
		
		try {
			questionDao = db.getQuestionDao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Question getById(Integer id) {
		
		try {
			QueryBuilder<Question, Integer> qb = questionDao.queryBuilder();
			
			qb.where().eq("id", id);
			
			PreparedQuery<Question> pq = qb.prepare();
			return questionDao.queryForFirst(pq);
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Question> getByTrain(Integer id) {
		
		try {
			return questionDao.queryForEq("train_id", id);
			
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Question> getAll() {
		
		 try {
             return questionDao.queryForAll();
	     } catch (java.sql.SQLException e) {
	             // TODO: Exception Handling
	             e.printStackTrace();
	     }
	     return null;
	}
}
