package com.archtypestudios.inforail.repositories;


import java.util.List;

import android.database.SQLException;

import com.archtypestudios.inforail.db.DatabaseHelper;
import com.archtypestudios.inforail.model.Train;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

public class TrainRepository {
	
	Dao<Train, Integer> trainDao;
	
	public TrainRepository(DatabaseHelper db) {
		
		try {
			trainDao = db.getTrainDao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Train getById(Integer id) {
		
		try {
			QueryBuilder<Train, Integer> qb = trainDao.queryBuilder();
			
			qb.where().eq("id", id);
			
			PreparedQuery<Train> pq = qb.prepare();
			return trainDao.queryForFirst(pq);
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Train> getAll() {
		
		 try {
             return trainDao.queryForAll();
	     } catch (java.sql.SQLException e) {
	             // TODO: Exception Handling
	             e.printStackTrace();
	     }
	     return null;
	}
}
