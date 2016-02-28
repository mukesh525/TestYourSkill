package com.mukesh.testyourskill.utils;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParsePush;
import com.parse.SaveCallback;

public class ParseApplication extends Application {
	public static  ParseApplication sInstance;
	@Override
	public void onCreate() {
		super.onCreate();
		sInstance = this;
		// Add your initialization code here
		Parse.initialize(this, "685JjiIkYC26h8ZQpPL39sx8qFi81gd8vy28pfgG", "LjRq0MqCcBHS8f9Xi0Nwo2NfLdGrK0LCT53jtTp6");
		ParsePush.subscribeInBackground("", new SaveCallback() {
			@Override
			public void done(ParseException e) {
				if (e == null) {
					Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
				} else {
					Log.e("com.parse.push", "failed to subscribe for push", e);
				}
			}
		});
	}

	public static  ParseApplication getInstance()
	{
		return  sInstance;
	}

	public static Context getAplicationContext()
	{
		return  sInstance.getApplicationContext();
	}
}
