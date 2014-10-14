package com.example.morsms;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.NameValuePair;


import android.os.AsyncTask;
import android.util.Log;

public class POSTThread extends AsyncTask<String, Void, String>{
	
	@Override
	protected String doInBackground(String... params) {
		try {
			URL url = new URL("https://devs.inboxtheapp.com/message");//API
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(10000);
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			//headers
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			//server token
			conn.setRequestProperty("Authorization", "bearer Md9livp1WJ6MWWnkkVolRbKWFSyuJ2nnxlmZPZ2NZ55qOS885pkZ3q3EnUiZ");
			//parameters, receiver and msg content
			ArrayList<org.apache.http.NameValuePair> paramsJS = new ArrayList<org.apache.http.NameValuePair>();
			paramsJS.add(new org.apache.http.message.BasicNameValuePair("to","jquinn"));
			paramsJS.add(new org.apache.http.message.BasicNameValuePair("text",params[0]));
			
			OutputStream os = conn.getOutputStream();
			BufferedWriter writer = new BufferedWriter(
			        new OutputStreamWriter(os, "UTF-8"));
			Log.v("msms-net",getQuery(paramsJS));
			writer.write(getQuery(paramsJS)); //write to connection stream
			writer.flush();
			writer.close();
			os.close();

			conn.connect(); //push
			Log.v("msms-net",conn.getResponseCode()+"");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}
	
	//builds key value pair for post
	private String getQuery(ArrayList<NameValuePair> params) throws Exception
	{
	    StringBuilder keyvalpair = new StringBuilder();
	    boolean firstparam = true;
	    for (NameValuePair pair : params)
	    {
	        if (!firstparam) {
	        	keyvalpair.append("&");
	        }
	        else {
	            firstparam = false;
	        }
	        keyvalpair.append(URLEncoder.encode(pair.getName(), "UTF-8"));
	        keyvalpair.append("=");
	        keyvalpair.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
	    }

	    return keyvalpair.toString();
	}
}




