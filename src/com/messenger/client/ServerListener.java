package com.messenger.client;

import com.messenger.common.Message;
import java.io.ObjectInputStream;
import java.io.IOException;


public class ServerListener implements Runnable {

	private final ObjectInputStream in;
	
	public ServerListener(ObjectInputStream in) {
		this.in = in;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Message incomingMsg;
			while(( incomingMsg = (Message)in.readObject()) != null) {
				System.out.println(incomingMsg);
			}
		}catch(IOException | ClassNotFoundException e) {
			System.err.println("\n 系統提示，已於伺服器中斷");
		}
	}

}
