package com.mukesh.testyourskill.parsing;

import com.mukesh.testyourskill.database.Ques_SetGet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Parsing {
	
	public static String jsonParserChangePassword(String responce) 
	{
		
        String Status="";
		
		try {
			if(responce!=null)
				{  
			           
			           JSONObject jresData=new JSONObject(responce);
			           String sattus=jresData.getString("status");
			           String code=jresData.getString("code");
			           String message=jresData.getString("message");
			           System.out.println("data.........."+jresData);
			           return message;
			     }
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					   
			
		return Status;
	}

    public static ArrayList<Ques_SetGet> GetSubject(String responce)
    {

        ArrayList<Ques_SetGet> categorySet = new ArrayList<Ques_SetGet>();

        try {
            if(responce!=null)
            {

                JSONArray json=new JSONArray(responce);

                try {

                    for(int i = 0; i <json.length(); i++)
                    {
                        JSONObject c = json.getJSONObject(i);
                        //String subject =
                        Ques_SetGet ItemSetGet=new Ques_SetGet();
                        ItemSetGet.setCategory(c.getString("Subject"));
                        categorySet.add(ItemSetGet);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return categorySet;
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return categorySet;
    } public static ArrayList<Ques_SetGet> GetSubjectVolley(JSONArray json)
      {
          ArrayList<Ques_SetGet> categorySet = new ArrayList<Ques_SetGet>();

        try {

                    for(int i = 0; i <json.length(); i++)
                    {
                        JSONObject c = json.getJSONObject(i);
                        Ques_SetGet ItemSetGet=new Ques_SetGet();
                        ItemSetGet.setCategory(c.getString("Subject"));
                        categorySet.add(ItemSetGet);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return categorySet;
     }






    public static ArrayList<Ques_SetGet> GetQuestionBySubject(String responce)
    {

        ArrayList<Ques_SetGet> categorySet = new ArrayList<Ques_SetGet>();

        try {
            if(responce!=null)
            {

                JSONArray json=new JSONArray(responce);

                try {

                    for(int i = 0; i <json.length(); i++)
                    {
                        JSONObject c = json.getJSONObject(i);
                        Ques_SetGet ItemSetGet = new Ques_SetGet();
                        ItemSetGet.setQues(c.getString("Question"));
                        ItemSetGet.setA(c.getString("A"));
                        ItemSetGet.setB(c.getString("B"));
                        ItemSetGet.setC(c.getString("C"));
                        ItemSetGet.setD(c.getString("D"));
                        ItemSetGet.setAnswer(c.getString("Answer"));

                        categorySet.add(ItemSetGet);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return categorySet;
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return categorySet;
    }

	public static String jsonParserUpdateProfile(String responce) 
	{
		
        String Status="";
		
		try {
			if(responce!=null)
				{  
			           
			           JSONObject jresData=new JSONObject(responce);
			           String sattus=jresData.getString("status");
			           String code=jresData.getString("code");
			           String message=jresData.getString("message");
			           System.out.println("data.........."+jresData);
			           return message;
			     }
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					   
			
		return Status;
	}
}
