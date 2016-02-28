package com.mukesh.testyourskill.parsing;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils {
	
	public static void showDialog(Context context, String msg)

	{
		new AlertDialog.Builder(context).setMessage(msg).setCancelable(false)
				.setPositiveButton("OK", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}
				}).show();

	}
	
	public static boolean checkInternetConnection(Context mContext) {
		
		 ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(mContext.CONNECTIVITY_SERVICE);
	        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	        return activeNetworkInfo != null;
	}

}
