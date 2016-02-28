package com.mukesh.testyourskill.database;

import android.app.Application;
import android.content.Context;
import android.database.SQLException;

import java.io.IOException;

public class ApplicationConstant extends Application {

	
	public DbHelper myDbHelper;


	@Override
	public void onCreate() {
		super.onCreate();
	}


	// Create database if 1st time run the application
	public void ReadyApplicationDatabase(Context context) {

		myDbHelper = new DbHelper(context);

		try {

			myDbHelper.createDataBase();

		} catch (IOException ioe) {

			throw new Error("Unable to create database");

		}

		try {

			myDbHelper.openDataBase();

		} catch (SQLException sqle) {

			throw sqle;

		}
	}
	
	
	@Override
	public void onTerminate() {
		super.onTerminate();
	}
	


}
