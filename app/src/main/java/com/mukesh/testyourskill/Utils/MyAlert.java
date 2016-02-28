package com.mukesh.testyourskill.utils;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.mukesh.testyourskill.R;

public class MyAlert extends DialogFragment
{
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{

      AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
      builder.setMessage("Are you sure you want to exit? ");
      setCancelable(false);
      builder.setNegativeButton(R.string.Cancel,new DialogInterface.OnClickListener() {
		
		@SuppressLint("NewApi") @Override
		public void onClick(DialogInterface dialog, int which) {
	    	dialog.dismiss();
			
		}
	   });
     
      builder.setPositiveButton(R.string.OK,new DialogInterface.OnClickListener(){

		@Override
		public void onClick(DialogInterface dialog, int which) {

         /*Intent intent=new Intent(Intent.ACTION_MAIN);
                intent.addCategory(intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);*/


            /*Intent intent=new Intent(getActivity(),Welcome.class);
            intent.putExtra("Exit",true);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);*/
                         System.exit(0);

         /* android.os.Process.killProcess(android.os.Process.myPid());
          Intent intent=new Intent(Intent.ACTION_MAIN);
          intent.addCategory(intent.CATEGORY_HOME);
           // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          startActivity(intent);*/


			
		}
      });
      
     Dialog dialog=builder.create();
		return dialog;
	   }
}
