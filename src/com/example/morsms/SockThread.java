package com.example.morsms;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SockThread implements Runnable{

	@Override
	public void run() {
		String message;
		Socket sock;
		InputStream is;
		try {
			sock = new Socket("https://inbox.ngroc.com",80);
		
			is = sock.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		while(true) {
			try {
				int data = is.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
}


