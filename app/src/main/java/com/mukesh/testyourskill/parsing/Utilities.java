package com.mukesh.testyourskill.parsing;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import org.apache.http.NameValuePair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Utilities {
	
	public static boolean FlgForCheckLateTananentOrExpireLease;
	private static Activity mAct;
	InetConnectionPOST con2,con;

	public Utilities(Activity act){
		this.mAct = act;
	}

	public String getPath(Uri uri) {
		
		String[] projection = { MediaStore.Images.Media.DATA };
		try{
			String StringPath=uri.toString();
			if(StringPath.contains(":/"))
			{
			Cursor cursor =  mAct.managedQuery(uri, projection, null, null, null);
//			 mAct.startManagingCursor(cursor);
			int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cursor.moveToFirst();
			return cursor.getString(column_index);
			}else
			{
				return uri.toString();
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return uri.toString();
		}
	}
	
public String getPath_video(Uri uri) {
		
		String[] projection = { MediaStore.Video.Media.DATA };
		try{
			String StringPath=uri.toString();
			if(StringPath.contains(":/"))
			{
			Cursor cursor =  mAct.managedQuery(uri, projection, null, null, null);
//			 mAct.startManagingCursor(cursor);
			int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
			cursor.moveToFirst();
			return cursor.getString(column_index);
			}else
			{
				return uri.toString();
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return uri.toString();
		}
	}
	
	public static void showPopup(Context ctx, String title, String msg, String btnName){
		 new AlertDialog.Builder(ctx)
			.setTitle(title)
			.setMessage(msg)
			.setPositiveButton(btnName, null)
			.show();
	}
	
	/**Call From Device **/
	public static void callNumber(Context mContext, String strNum){
		if(strNum.length()>0)
		{
			if(strNum.contains("-") | strNum.contains("(") | strNum.contains(")") | strNum.contains(" "))
				strNum = (((strNum.replace("-", "")).replace("(", "")).replace(")", "")).replace(" ", "");
			
			try{
				Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + strNum));
		        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        mContext.startActivity(intent);
//				Uri number = Uri.parse("tel:" + strNum);
//				Intent intent = new Intent(Intent.ACTION_CALL, number);
//				mContext.startActivity(intent);	
			}catch(Exception e){
				Toast.makeText(mContext, "Phone Call not possible", Toast.LENGTH_SHORT).show();
			}
			
			
		}
	}
	
	/** showWeb**/
	public static void showWeb(Context mContext, String url){
		if(url.length()>0){
		
			try {
				if (!url.startsWith("https://") && !url.startsWith("http://")){
				    url = "http://" + url;
				}
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				mContext.startActivity(i);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	/** Show Map**/
	public static void showMap(Context mContext, String str){
		if(str.length()>0){
			String query;
			try {
				query = URLEncoder.encode(str, "utf-8");
				String url = "http://maps.google.com/?q=" + query;
				
				Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
				mContext.startActivity(intent1);
				
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	/** Send Email**/
	
	public static void emailShare(Context mContext,String emailText) {
		if(emailText.length()>0){
			Intent shareIntent = new Intent(Intent.ACTION_SEND);
			shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
			shareIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailText});
			shareIntent.putExtra(Intent.EXTRA_SUBJECT, "RentTracker");
			shareIntent.putExtra(Intent.EXTRA_TEXT, "Send mail from Android");//this.getResources().getString(R.string.share_app_str));
			
			shareIntent.setType("text/xml");
			mContext.startActivity(shareIntent);
		}
	}
	
	
	
	public static String getImgSizeHeightWidth(String imageURI, Context context){
		
//		ContentResolver resolver = context.getContentResolver();
		InputStream is;
		String size = null;
		try {
			File file = new File(imageURI);
			is = new FileInputStream(file);
//			is = resolver.openInputStream(ImageURI);
			BitmapFactory.Options bounds = new BitmapFactory.Options();
			bounds.inJustDecodeBounds = true; 
			BitmapFactory.decodeStream(is,null,bounds);
			int width = bounds.outWidth;
			int height = bounds.outHeight;
			size = String.valueOf(width)+"|"+String.valueOf(height);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return size;
	}
	
	public static String dateAndTime(String whatNeed)
	{
		
		 Calendar cal = Calendar.getInstance(); 
		 StringBuilder date= new StringBuilder(); 
		   int millisecond = cal.get(Calendar.MILLISECOND);
		   int second = cal.get(Calendar.SECOND);
		   int minute = cal.get(Calendar.MINUTE);
		         //12 hour format
		   int hour = cal.get(Calendar.HOUR);
		         //24 hour format
		   int hourofday = cal.get(Calendar.HOUR_OF_DAY);
		   int am_pm = cal.get(Calendar.AM_PM);
		   
		     int mYear = cal.get(Calendar.YEAR);
		     int mMonth = cal.get(Calendar.MONTH);
		     int mDay =   cal.get(Calendar.DAY_OF_MONTH);
		     if("date".equalsIgnoreCase(whatNeed))
		     {
		    	  
				   if((mMonth + 1)<=9)
				   date.append("0"+(mMonth +1));
				   else
				    date.append(mMonth + 1);
				   if((mDay)<=9)
				   date.append("/").append("0"+mDay);
				   else
				    date.append("/").append(mDay);
				   
				   date.append("/").append(mYear).append(" ");   
				  
		     }else  if("time".equalsIgnoreCase(whatNeed))
		     {
		      if(am_pm==0)
		    	  date.append(hour+":"+minute+":"+second+"  "+"AM");
		       
		      else
		    	  date.append(hour+":"+minute+":"+second+"  "+"PM");		       
		     }
	 return date.toString(); 
	}
	
	public static int dateDiff(int startYear,int startMonth,int startDay)
	 {
	   int retVal=-1;
	  Calendar startDate = Calendar.getInstance();
	  startDate.set(Calendar.DAY_OF_MONTH,startDay);
	  startDate.set(Calendar.MONTH,startMonth); 
	  startDate.set(Calendar.YEAR, startYear);  
	  startDate.set(Calendar.HOUR,0);
	  startDate.set(Calendar.MINUTE,0);
	  startDate.set(Calendar.SECOND,0);
	  startDate.set(Calendar.MILLISECOND,0);
	  
	  String str=startDate.get(Calendar.YEAR)+""+startDate.get(Calendar.MONTH)+""+startDate.get(Calendar.DAY_OF_MONTH);

	    Calendar today = Calendar.getInstance();
	    today.set(Calendar.DAY_OF_MONTH,today.get(Calendar.DAY_OF_MONTH));
	    today.set(Calendar.HOUR,0);
	    today.set(Calendar.MINUTE,0);
	    today.set(Calendar.SECOND,0);
	    today.set(Calendar.MILLISECOND,0);
	    String str1=today.get(Calendar.YEAR)+""+today.get(Calendar.MONTH)+""+today.get(Calendar.DAY_OF_MONTH);
	   // long diff = today.getTimeInMillis() - startDate.getTimeInMillis();
	  /*  if (startDate != null && today != null) {
	             int retVal = today.compareTo(startDate);

	             if (retVal > 0)
	                 return 1; // date1 is greatet than date2
	             else if (retVal == 0) // both dates r equal
	                 return 0;
	             // Result will be > 0 if date1 > date2, = 0 if date1 = date2, < 0 if date1 < date2.             
	         }*/
	    
	   // ===========================================================
	   
	    if(today.before(startDate))
	   /*  System.out.print("Current date(" + new SimpleDateFormat("dd/MM/yyyy").format(startDate.getTime()) + ") is greater than the given date " + new
	     SimpleDateFormat("dd/MM/yyyy").format(today.getTime()));*/
	     return -1;
	     else if(today.after(startDate))
	     {
	      
	      long milliseconds1 = startDate.getTimeInMillis();
	      long milliseconds2 = today.getTimeInMillis();
	      long diff = milliseconds2 - milliseconds1;
	      long diffDays = diff / (24 * 60 * 60 * 1000);
	      System.out.println("Time in days: " + diffDays  + " days.");     
	     
	      if(diffDays>=1)
	      {
	       return 1;  
	      }else if(diffDays<=0)
	      {
	       return -1;   
	      }
	      
	       
	     }
	     /*System.out.print("Current date(" + new SimpleDateFormat("dd/MM/yyyy").format(startDate.getTime()) + ") is less than the given date " + new 
	     SimpleDateFormat("dd/MM/yyyy").format(today.getTime()));*/
	      
	     else
	     System.out.print("Both date are equal.");
	       return 0;
	    //===========================================================
	         
	 }
	
	
	 public static String saveImageFromDrable(Context con,int imageName,String fileName,boolean overwrite) {
	        File filename;
	        String fullPath = null;
	        try {
	            String path = Environment.getExternalStorageDirectory().toString();

	            new File(path + "/subfolder").mkdirs();
	            filename = new File(path + "/subfolder/"+fileName); //image.jpg           
	            fullPath = filename.getAbsolutePath();
	            if(!filename.exists()){
	             FileOutputStream out = new FileOutputStream(filename);
	             Bitmap bmImg= BitmapFactory.decodeResource(con.getResources(),imageName);
	            
	             bmImg.compress(Bitmap.CompressFormat.JPEG, 90, out);
	             out.flush();
	             out.close();
	 
	             MediaStore.Images.Media.insertImage(con.getContentResolver(),
	                     filename.getAbsolutePath(), filename.getName(),
	                     filename.getName());
	 
	            }else if(overwrite)
	            {
	              FileOutputStream out = new FileOutputStream(filename);
	               Bitmap bmImg= BitmapFactory.decodeResource(con.getResources(),imageName);
	             
	              bmImg.compress(Bitmap.CompressFormat.JPEG, 90, out);
	              out.flush();
	              out.close();
	  
	              MediaStore.Images.Media.insertImage(con.getContentResolver(),
	                      filename.getAbsolutePath(), filename.getName(),
	                      filename.getName());
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return fullPath;    
	    }
	
	/************************************ All methods are used for Report **********************************/
	
		/****** Alert & Alert Report ****/
		
	
	public static String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException
	{
	    StringBuilder result = new StringBuilder();
	    boolean first = true;

	    for (NameValuePair pair : params)
	    {
	        if (first)
	            first = false;
	        else
	            result.append("&");

	        result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
	        result.append("=");
	        result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
	    }

	    return result.toString();
	}


	
	public static String convertStreamToString(InputStream is) {
	    String line = "";
	    StringBuilder total = new StringBuilder();
	    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	    try {
	        while ((line = rd.readLine()) != null) {
	            total.append(line);
	        }
	    } catch (Exception e) {
	       // Toast.makeText(Utilities.this, "Stream Exception", Toast.LENGTH_SHORT).show();
	    	System.out.println(e);
	    }
	    return total.toString();
	}
	
	public static Bitmap rotate(Bitmap bitmap, int degree) {
	    int w = bitmap.getWidth();
	    int h = bitmap.getHeight();

	    Matrix mtx = new Matrix();
	    mtx.postRotate(degree);

	    return Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, true);
	}
	
}
