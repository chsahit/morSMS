package com.example.morsms;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public class SadThread extends AsyncTask<String, Void, String>{
	

//	@Override
//	protected HttpResponse doInBackground(String... params) {
//		HttpClient httpclient = new DefaultHttpClient();
//		try {
//			HttpPost request = new HttpPost("https://devs.inboxtheapp.com/message");
//			//StringEntity paramsJS = new StringEntity("POST={\"to\":\"jquinn\",\"text:\":\"hw\"}");
//			StringEntity paramJS = new StringEntity("");
//			request.setEntity(paramJS);
//			request.addHeader("Content-Type", "application/json");
//			request.addHeader("Authorization", "bearer Md9livp1WJ6MWWnkkVolRbKWFSyuJ2nnxlmZPZ2NZ55qOS885pkZ3q3EnUiZ");
//			HttpResponse response = httpclient.execute(request);
//			Log.v("msms-net",response.getEntity().toString());
//			return response;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			Log.v("msms - net",	e.toString());
//		}
//		return null;
//	}
	@Override
	protected String doInBackground(String... params) {
		try {
			URL url = new URL("https://devs.inboxtheapp.com/message");
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(15000);
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Authorization", "bearer Md9livp1WJ6MWWnkkVolRbKWFSyuJ2nnxlmZPZ2NZ55qOS885pkZ3q3EnUiZ");
			ArrayList<org.apache.http.NameValuePair> paramsJS = new ArrayList<org.apache.http.NameValuePair>();
			paramsJS.add(new org.apache.http.message.BasicNameValuePair("to","jquinn"));
			paramsJS.add(new org.apache.http.message.BasicNameValuePair("text",params[0]));
			
			OutputStream os = conn.getOutputStream();
			BufferedWriter writer = new BufferedWriter(
			        new OutputStreamWriter(os, "UTF-8"));
			Log.v("msms-net",getQuery(paramsJS));
			writer.write(getQuery(paramsJS));
			writer.flush();
			writer.close();
			os.close();

			conn.connect();
			Log.v("msms-net",conn.getResponseCode()+"");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}
	
	private String getQuery(ArrayList<NameValuePair> params) throws Exception
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
}




