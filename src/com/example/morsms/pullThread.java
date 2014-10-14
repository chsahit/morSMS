package com.example.morsms;

import java.io.IOException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import android.os.AsyncTask;
import android.util.Log;

public class pullThread extends AsyncTask<String, Void, Void>{
boolean stop;
protected Void doInBackground(String... params) {
	Log.v("msms", "asd");
	HttpsURLConnection conn;
	try{
		URL url = new URL("https://devs.inboxtheapp.com/message?chat_id=543a3061508870bc237f6641");
		conn = (HttpsURLConnection) url.openConnection();
		conn.setReadTimeout(10000);
		conn.setConnectTimeout(15000);
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		conn.setRequestProperty("Authorization", "bearer Md9livp1WJ6MWWnkkVolRbKWFSyuJ2nnxlmZPZ2NZ55qOS885pkZ3q3EnUiZ");
	} catch (Exception e) {
		Log.v("msms","sad");
		return null;
	}
	while(!stop) {
		if(true) {
			try {
				System.out.println("reached");
				conn.connect();
//				Log.v("msms - n",conn.getContent()[]);
			} catch (IOException e) {
				Log.v("msms","failed");
			}
		}
	}
	return null;
}



}
