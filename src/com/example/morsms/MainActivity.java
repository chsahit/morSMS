package com.example.morsms;

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

			"...-", ".--", "-..-", "-.--", "--..", ".----", "..---", "...--"
			, "....-", ".....", "-....", "--...", "---..", "----.", "-----"};
	String[] alphabet = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
			"k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
			"x", "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};

	String message = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//new pullThread().execute();
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
	/**
	 * parses morse code
	 * @param message string made up of dots and dashes
	 * 			one space separates letter, 2 for words
	 * @return
	 */
	public String parseMorse(String message) {
		String parsed = "";
		boolean cont = true;
		while(cont) {
			int wordIndex = message.indexOf("  ");//find word sep
			String word = "";
			//last word found
			if (wordIndex == -1) {
				word = message;
				cont = false; 
			} else {
				word = message.substring(0, wordIndex); //isolate word
			}
			while(true) {
				int letterIndex = word.indexOf(" "); //find letter
				String letter = "";
				boolean breaklater = false;
				if(letterIndex == -1) {
					letter = word;
					breaklater = true;
				} else {
					letter = word.substring(0,letterIndex);
				}
				//match letter in morse to letter in english
				for(int i = 0;i < morse.length; i++) {
					if(letter.equals(morse[i])) {
						parsed+=alphabet[i];
						break;
					}
				}
				if(breaklater) {
					break;
				}
				//removed parse letter
				word = word.substring(letterIndex+1, word.length());
//				System.out.println("splitl "  + word);
			}
			parsed += " ";
			//remove parsed word
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
	/**
	 * sends inputted string
	 * 	1. parse morse
	 *  2. POST request to index
	 * @param view derp
	 */
	public void send(View view) {
		String parsedMessage = parseMorse(message);
		Log.v("msms", message);
		Log.v("msms - p",parsedMessage);
		new POSTThread().execute(parsedMessage);
	}
}
