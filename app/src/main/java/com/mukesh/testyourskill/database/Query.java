package com.mukesh.testyourskill.database;


import android.content.ContentValues;
import android.database.Cursor;



import java.util.ArrayList;


public class Query {

	ApplicationConstant app;
	
	public Query(ApplicationConstant obj){
		app=obj;
	}
	
	
    public ArrayList<Ques_SetGet> GetSubject(){

        if(app.myDbHelper.MyDB()==null)
            app.myDbHelper.openDataBase();

        String SQL="SELECT * FROM exam Group by Subject";
        Cursor cursor =app.myDbHelper.MyDB().rawQuery(SQL, null);

        ArrayList<Ques_SetGet> SubjectDetails = null;

        if(cursor.getCount() >0)
        {
            SubjectDetails=new ArrayList<Ques_SetGet>();

            while (cursor.moveToNext()) {
                try {

                    Ques_SetGet obj = new Ques_SetGet();
                    obj.setCategory(cursor.getString(cursor.getColumnIndex("Subject")));
                    SubjectDetails.add(obj);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return SubjectDetails;
    }
    public ArrayList<Ques_SetGet> GetQuesBySubject(String Subject,int NoOfQuestions){

        if(app.myDbHelper.MyDB()==null)
            app.myDbHelper.openDataBase();

        String SQL="SELECT * FROM exam where Subject ='"+ Subject +"'ORDER BY RANDOM() LIMIT '"+ NoOfQuestions +"'";
        Cursor cursor =app.myDbHelper.MyDB().rawQuery(SQL, null);

        ArrayList<Ques_SetGet> QuesDetails = null;

        if(cursor.getCount() >0)
        {
            QuesDetails=new ArrayList<Ques_SetGet>();

            while (cursor.moveToNext()) {
                try {

                    Ques_SetGet obj = new Ques_SetGet();
                    obj.setCategory(cursor.getString(cursor.getColumnIndex("Subject")));
                    obj.setQues(cursor.getString(cursor.getColumnIndex("Question")));
                    obj.setID(cursor.getString(cursor.getColumnIndex("Id")));
                    obj.setA(cursor.getString(cursor.getColumnIndex("A")));
                    obj.setB(cursor.getString(cursor.getColumnIndex("B")));
                    obj.setC(cursor.getString(cursor.getColumnIndex("C")));
                    obj.setD(cursor.getString(cursor.getColumnIndex("D")));
                    obj.setAnswer(cursor.getString(cursor.getColumnIndex("Answer")));

                    QuesDetails.add(obj);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return QuesDetails;
    }
	
	

	public boolean insertData(String username, String password,String phone,String firstname, String lastname,String email)
    {
	
		ContentValues contentValue = new ContentValues();		
		contentValue.put("username",username);
		contentValue.put("password",password);
		contentValue.put("phone",phone);
        contentValue.put("firstname",firstname);
        contentValue.put("lastname",lastname);
        contentValue.put("email",email);
				
		long Success = -1;
		try {
			Success = app.myDbHelper.MyDB().insert("userdata", null, contentValue);
		} catch (Exception e) {
			Success = -1;
		}
	
		if(Success>-1)
			return true;
		else
			return false;
	}
	
	public boolean UpdateData(String username, String password) {
		
		ContentValues contentValue = new ContentValues();
		
		contentValue.put("password",password);
			
		
		long Success = -1;
		try {
			Success = app.myDbHelper.MyDB().update("userdata",contentValue,"username"+"=?" , new String[] {username});
		} catch (Exception e) {
			Success = -1;
		}
	
		if(Success>-1)
			return true;
		else
			return false;
	}

public boolean Delte(String username) {
		
		//ContentValues contentValue = new ContentValues();
		
		//contentValue.put("password",password);
			
		
		long Success = -1;
		try {
			Success = app.myDbHelper.MyDB().delete("usertable","username"+"=?" , new String[] {username});
		} catch (Exception e) {
			Success = -1;
		}
	
		if(Success>-1)
			return true;
		else
			return false;
	}
	
	 //---------------------------------------------------------------------------------------------
	/*  public int deleterow(){
		  
		  SQLiteDatabase db=helper.getWritableDatabase();
		  String[] whereArgs={"tintin"};
		  int count=db.delete(Helper.TABLE_NAME, Helper.NAME+"=?", whereArgs);
		  return count;
		  
		  
	  }*/
	  //----------------------------------------------------------------------------------------------------
	public boolean deleteData(){
			
			String Query = "DELETE FROM EmployeeDetails";	
			Cursor cursor = null;
			
			try {
				cursor = app.myDbHelper.MyDB().rawQuery(Query, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(cursor!=null && cursor.getCount()>0){
				cursor.close();
				return true;
			}
			else{
				cursor.close();
				return false;
			}
		}
	
	
	public boolean IsUserExists(String username){
		
		String Query = "select username from userdata where username='"+ username +"'";
		Cursor cursor = null;
		
		try {
			cursor = app.myDbHelper.MyDB().rawQuery(Query, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(cursor!=null && cursor.getCount()>0){
			cursor.close();
			return true;
		}
		else{
			cursor.close();
			return false;
		}
	}
	
public boolean Login(String username,String Password){
		
		String Query = "select username from userdata where username='"+ username +"' and password='"+ Password +"'";
		Cursor cursor = null;
		
		try {
			cursor = app.myDbHelper.MyDB().rawQuery(Query, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(cursor!=null && cursor.getCount()>0){
			cursor.close();
			return true;
		}
		else{
			cursor.close();
			return false;
		}
	}

/*public ArrayList<User_SetGet> getdata(String username) {

    // SQLiteDatabase db=helper.getWritableDatabase();
    String[] columns = {"password", "phone", "email","firstname", "lastname"};

    Cursor cursor = app.myDbHelper.MyDB().query("userdata", columns, "username" + " = '" + username + "'", null, null, null, null);
    ArrayList<User_SetGet> UserDetails = null;
    if (cursor.getCount() > 0) {
        UserDetails = new ArrayList<User_SetGet>();

        while (cursor.moveToNext()) {
            try {

                User_SetGet obj = new User_SetGet();

                obj.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                obj.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                obj.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
                obj.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                obj.setFirstname(cursor.getString(cursor.getColumnIndex("firstname")));
                obj.setLasttname(cursor.getString(cursor.getColumnIndex("lastname")));
                UserDetails.add(obj);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    if (cursor != null && !cursor.isClosed()) {
        cursor.close();
    }

    return UserDetails;
}*/
public Ques_SetGet getdata(String username){

    // SQLiteDatabase db=helper.getWritableDatabase();
    Ques_SetGet user=new Ques_SetGet();

    String[] columns={"password","phone","email","firstname","lastname"};

    Cursor cursor=  app.myDbHelper.MyDB().query("userdata", columns,"username"+" = '"+username+"'", null, null,null,null);
    StringBuffer b=new StringBuffer();
    while(cursor.moveToNext()) {

        int index1 = cursor.getColumnIndex("Cateogry");
        int index2 = cursor.getColumnIndex("Desc");
        int index3 = cursor.getColumnIndex("Read");

        /*user.setPassword(cursor.getString(index1));
        user.setPhone(cursor.getString(index2));
        user.setFirstname(cursor.getString(index4));*/
    }
    return user;



}
}

