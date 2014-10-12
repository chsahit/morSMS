package com.example.morsms;

import java.io.IOException;
import java.net.URL;
import java.util.Timer;

import javax.net.ssl.HttpsURLConnection;

import android.os.AsyncTask;
import android.util.Log;

public class pullThread extends AsyncTask<String, Void, Void>{
boolean stop;
protected Void doInBackground(String... params) {
	Log.v("msms", "asd");
	Timer time = new Timer();
	long start = System.currentTimeMillis();
	HttpsURLConnection conn;
	try{
		URL url = new URL("https://devs.inboxtheapp.com/message?chat_id=543a3061508870bc237f6641");
		conn = (HttpsURLConnection) url.openConnection();
		conn.setReadTimeout(10000);
		conn.setConnectTimeout(15000);
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Authorization", "bearer Md9livp1WJ6MWWnkkVolRbKWFSyu");
	} catch (Exception e) {
		Log.v("msms","sad");
		return null;
	}
	while(!stop) {
		long curr = System.currentTimeMillis();
		//System.out.println(curr);
		if(true) {
			try {
				System.out.println("reached");
				conn.connect();
				Log.v("msms - n",conn.getContent().toString());
			} catch (IOException e) {
				Log.v("msms","failed");
			}
		}
	}
	return null;
}
public void onPause() {
	stop = true;
}


}
