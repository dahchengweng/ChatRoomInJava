package com.messenger.client;

import com.messenger.common.Message;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class ChatClient {

	private final String host;
	private final int port;
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private String username;
	
	//建構子
	public ChatClient(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public void start() {
		
		Scanner scanner = new Scanner(System.in);
		
		//1.輸入我的使用者名稱
		System.out.print("請輸入你進入聊天室的暱稱: ");
		this.username = scanner.nextLine();
		
		try {
			System.out.println("正在連線到伺服器" + host + ":" + port + "...");
			this.socket = new Socket(host,port);
			this.in = new ObjectInputStream(socket.getInputStream());
			this.out = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("連線成功，已進入聊天室");
			//2.先跟伺服器回報username
			out.writeObject(username);
			out.flush();
			
			ServerListener listener = new ServerListener(in);
			Thread listenerThread = new Thread(listener);
			listenerThread.start();
			while(true) {
				
				String input = scanner.nextLine();
				if("exit".equalsIgnoreCase(input.trim())) {
					System.out.println("正在登出聊天室");
					break;
				}
				
				if(!input.trim().isEmpty()) {
					String sender = username + " 說: ";
					Message message = new Message(sender, input);
					out.writeObject(message);
					out.flush();
				} 
			}
		}catch(IOException e) {
			System.err.println("網路連線異常 : " + e.getMessage());
		}finally {
			closeConnections();
			scanner.close();
		}
		
		
		
		
	}
	
	private void closeConnections() {
		try {
			if(out!= null) out.close(); 
			if(in != null) in.close();
			if(socket!=null && !socket.isClosed()) socket.close();
			System.out.println("已安全中斷連線");
		}catch(IOException e) {
			System.out.println("關閉連線 : " + e.getMessage());
		}
		
	}
	
	public static void main(String[] args) {
		ChatClient client = new ChatClient("127.0.0.1",31245);
		client.start();
	}
	
	
}
