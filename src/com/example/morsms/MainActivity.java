package com.example.morsms;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.NameValuePair;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ActionBarActivity {
	String[] morse = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....",
			"..",".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.",
			"...", "-", "..-",

			"...-", ".--", "-..-", "-.--", "--..", "|" };
	String[] alphabet = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
			"k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
			"x", "y", "z", " " };

	String message = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new pullThread().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}	
	public String parseMorse(String message) {
		String parsed = "";
		boolean cont = true;
		while(cont) {
			int wordIndex = message.indexOf("  ");
			String word = "";
			if (wordIndex == -1) {
				word = message;
				cont = false;
			} else {
				word = message.substring(0, wordIndex);
			}
			while(true) {
				int letterIndex = word.indexOf(" ");
				String letter = "";
				boolean breaklater = false;
				if(letterIndex == -1) {
					letter = word;
					breaklater = true;
				} else {
					letter = word.substring(0,letterIndex);
				}
				for(int i = 0;i < morse.length; i++) {
					if(letter.equals(morse[i])) {
						parsed+=alphabet[i];
						break;
					}
				}
				if(breaklater) {
					break;
				}
				word = word.substring(letterIndex+1, word.length());
				System.out.println("splitl "  + word);
			}
			parsed += " ";
			message = message.substring(wordIndex + 1,message.length());
		}
		return parsed;
	}


	public void putDash(View view) {
		message += "-";
	}

	public void putDot(View view) {
		message += ".";
	}

	public void putSpace(View view) {
		message += " ";
	}

	public void send(View view) {
		String parsedMessage = parseMorse(message);
		Log.v("msms", message);
		Log.v("msms - p",parsedMessage);
		message = "";
		new SadThread().execute(parsedMessage);
	}
}
