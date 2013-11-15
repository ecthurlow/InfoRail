package com.archtypestudios.inforail.repositories;


import java.util.List;

import android.database.SQLException;

import com.archtypestudios.inforail.db.DatabaseHelper;
import com.archtypestudios.inforail.model.TrainInfo;
import com.archtypestudios.inforail.model.TrainPart;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;

public class TrainPartRepository {
	
	Dao<TrainPart, Integer> trainPartDao;
	
	public TrainPartRepository(DatabaseHelper db) {
		
		try {
			trainPartDao = db.getTrainPartDao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public TrainPart getById(Integer id) {
		
		try {
			QueryBuilder<TrainPart, Integer> qb = trainPartDao.queryBuilder();
			
			qb.where().eq("id", id);
			
			PreparedQuery<TrainPart> pq = qb.prepare();
			return trainPartDao.queryForFirst(pq);
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<TrainPart> getByTrain(Integer id) {
		
		try {
			return trainPartDao.queryForEq("train_id", id);
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<TrainPart> getAllWon() {
		
		try {
			return trainPartDao.queryForEq("won", true);
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<TrainPart> getAll() {
		
		 try {
             return trainPartDao.queryForAll();
	     } catch (java.sql.SQLException e) {
	             // TODO: Exception Handling
	             e.printStackTrace();
	     }
	     return null;
	}
	
	public void updateTrainPart(TrainPart trainPart) {
		
		try {
			trainPartDao.update(trainPart);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		catch(java.sql.SQLException e) {
			e.printStackTrace();
		}
	}
}
