package com.archtypestudios.inforail.db;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseInitializer extends SQLiteOpenHelper {
	
	private static String DB_PATH = "/assets/databases";
	private static String DB_NAME = "InfoRailDB.sqlite";
	
	private SQLiteDatabase database;
	private final Context context;
	
	public DatabaseInitializer(Context context) {
		super(context, DB_NAME, null, 1);
		this.context = context;
	}
	
	public void createDatabase() throws IOException {
		
		boolean dbExist = checkDatabase();
		
		if(!dbExist) {
			this.getReadableDatabase();
			try {
				copyDatabase();
			} catch (IOException e ) {
				throw new Error("Error copying database", e);
			}
		}
	}
	
	private boolean checkDatabase() {
		
		SQLiteDatabase checkDB = null;
		
		try {
			String dbLocation = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(dbLocation, null, SQLiteDatabase.OPEN_READONLY);
		} catch(SQLException e) {
			Log.e(DatabaseInitializer.class.getName(), "Error finding database", e);
		}
		
		if(checkDB != null) {
			checkDB.close();
		}
		
		return checkDB != null ? true : false;
	}
	
	private void copyDatabase() throws IOException {
		
		InputStream myInput = context.getAssets().open(DB_NAME);
		
		String outFileName = DB_PATH + DB_NAME;
		
		OutputStream myOutput = new FileOutputStream(outFileName);
		
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0 ) {
			myOutput.write(buffer, 0, length);
		}
		
		myOutput.flush();
		myOutput.close();
		myInput.close();
	}
	
	@Override
	public synchronized void close() {
		
		if(database != null) {
			database.close();
			super.close();
		}
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
