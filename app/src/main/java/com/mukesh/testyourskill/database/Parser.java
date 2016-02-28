package com.mukesh.testyourskill.database;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Parser 
{
   static JSONArray jobj=null;
   static String json=null;
   static InputStream is=null;
   
   public static JSONArray getJasonFromUrl(String url)
   {
	   try {
		DefaultHttpClient client=new DefaultHttpClient();
		   HttpPost post=new HttpPost(url);
		   HttpResponse response=client.execute(post);
		   HttpEntity entity=response.getEntity();
		   is=entity.getContent();
	} catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalStateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	   
	   try {
		BufferedReader reader=new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
		   StringBuffer sb=new StringBuffer();
		   String line=null;
		   while((line=reader.readLine())!=null)
		   {
			   sb.append(line+"\n");
		   }
		   is.close();
		   json=sb.toString();
		  jobj=new JSONArray(json);
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	  
	   return jobj;
   }
    public static JSONArray InsertJSONToUrl(String url,String subject) {

        // Making HTTP request
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();

            // HttpPost httpPost = new HttpPost(url);
            HttpPost httpPost = new HttpPost(url);

            // HttpGet request = new HttpGet(url);

            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
               nameValuePair.add(new BasicNameValuePair("Subject",subject));
               httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // try parse the string to a JSON object
        try {
            jobj = new JSONArray(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String
        return jobj;

    }
    public static JSONArray InsertJSONToUrlTransac(String url,String cardno,String cvcCode,String expDate,String amount,String emailId,String address) {

        // Making HTTP request
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();

            // HttpPost httpPost = new HttpPost(url);
            HttpPost httpPost = new HttpPost(url);

            // HttpGet request = new HttpGet(url);

            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
            nameValuePair.add(new BasicNameValuePair("cardNo",cardno));
            nameValuePair.add(new BasicNameValuePair("cvvCode",cvcCode));
            nameValuePair.add(new BasicNameValuePair("expdate",expDate));
            nameValuePair.add(new BasicNameValuePair("amount",amount));
            nameValuePair.add(new BasicNameValuePair("emailid",emailId));
            nameValuePair.add(new BasicNameValuePair("address",address));


            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // try parse the string to a JSON object
        try {
            jobj = new JSONArray(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String
        return jobj;

    }

    public static void InsertJSONToUrlTran(String url,String cardno,String cvcCode,String expDate,String amount,String emailId,String address) {

        // Making HTTP request
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();

            // HttpPost httpPost = new HttpPost(url);
            HttpPost httpPost = new HttpPost(url);

            // HttpGet request = new HttpGet(url);

            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
            nameValuePair.add(new BasicNameValuePair("cardNo",cardno));
            nameValuePair.add(new BasicNameValuePair("cvvCode",cvcCode));
            nameValuePair.add(new BasicNameValuePair("expdate",expDate));
            nameValuePair.add(new BasicNameValuePair("amount",amount));
            nameValuePair.add(new BasicNameValuePair("emailid",emailId));
            nameValuePair.add(new BasicNameValuePair("address",address));


            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // try parse the string to a JSON object
        try {
            jobj = new JSONArray(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }



    }


}
