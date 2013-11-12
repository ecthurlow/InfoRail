package com.archtypestudios.inforail.repositories;


import java.util.List;

import android.database.SQLException;

import com.archtypestudios.inforail.db.DatabaseHelper;
import com.archtypestudios.inforail.model.TrainInfo;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

public class TrainInfoRepository {
	
	Dao<TrainInfo, Integer> trainInfoDao;
	
	public TrainInfoRepository(DatabaseHelper db) {
		
		try {
			trainInfoDao = db.getTrainInfoDao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public TrainInfo getById(Integer id) {
		
		try {
			QueryBuilder<TrainInfo, Integer> qb = trainInfoDao.queryBuilder();
			
			qb.where().eq("id", id);
			
			PreparedQuery<TrainInfo> pq = qb.prepare();
			return trainInfoDao.queryForFirst(pq);
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<TrainInfo> getByTrain(Integer id) {
		
		try {
			return trainInfoDao.queryForEq("train_id", id);
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<TrainInfo> getAll() {
		
		 try {
             return trainInfoDao.queryForAll();
	     } catch (java.sql.SQLException e) {
	             // TODO: Exception Handling
	             e.printStackTrace();
	     }
	     return null;
	}
}
