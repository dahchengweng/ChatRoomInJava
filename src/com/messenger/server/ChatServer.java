package com.messenger.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

	private final int port;
	private final ServerManager manager;
	
//	Construct by input port number
	public ChatServer(int port) {
		this.port = port;
		this.manager = new ServerManager();
	}
	
	public void start() {
		System.out.println("聊天室正在啟動：　監聽 port " + port + "...");
		try(ServerSocket serverSocket = new ServerSocket(port)){
			System.out.println(" 伺服器啟動成功，等待客戶連線");
			while(true) {
				Socket clientSocket = serverSocket.accept();
				ClientHandler handler = new ClientHandler(clientSocket, manager);
				Thread thread = new Thread(handler);
				thread.start();
			}
		}catch(IOException e) {
			System.err.println("伺服器異常中斷 : " + e.getMessage());
		}
	}
	
	
	public static void main(String[] args) {
		int port = 31245;
		ChatServer server = new ChatServer(port);
		server.start();
	}
	
}
