package com.archtypestudios.inforail.db;


import com.j256.ormlite.android.apptools.OpenHelperManager;

import android.content.Context;

public class DatabaseManager {
	
	private DatabaseHelper helper;
	
	public DatabaseHelper getHelper(Context context) {
		
		if(helper == null) {
			//FIX
			helper = (DatabaseHelper) OpenHelperManager.getHelper(context, DatabaseHelper.class);
		}
		
		return helper;
	}
	
	public void releaseHelper(DatabaseHelper helper) {
		
		if (helper != null) {
			OpenHelperManager.releaseHelper();
			helper = null;
		}
	}
}
