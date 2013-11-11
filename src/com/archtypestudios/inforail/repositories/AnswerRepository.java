package com.archtypestudios.inforail.repositories;

import java.util.List;

import android.database.SQLException;

import com.archtypestudios.inforail.db.DatabaseHelper;
import com.archtypestudios.inforail.model.Answer;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

public class AnswerRepository {

	Dao<Answer, Integer> answerDao;
	
	public AnswerRepository(DatabaseHelper db) {
		
		try {
			answerDao = db.getAnswerDao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Answer getById(Integer id) {
		
		try {
			QueryBuilder<Answer, Integer> qb = answerDao.queryBuilder();
			
			qb.where().eq("id", id);
			
			PreparedQuery<Answer> pq = qb.prepare();
			return answerDao.queryForFirst(pq);
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Answer> getByQuestion(Integer id) {
		
		try {
			return answerDao.queryForEq("question_id", id);
			
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Answer> getAll() {
		
		 try {
            return answerDao.queryForAll();
	     } catch (java.sql.SQLException e) {
	             // TODO: Exception Handling
	             e.printStackTrace();
	     }
	     return null;
	}
}
