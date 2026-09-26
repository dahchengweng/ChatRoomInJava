package com.messenger.server;

//引入io跟Socket
import java.io.*;
import java.net.Socket;
import com.messenger.common.Message;

public class ClientHandler implements Runnable{

	private final Socket socket;
	private final ServerManager manager;
	private String username;
	
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	//建構子
	public ClientHandler(Socket socket, ServerManager manager) {
		this.socket = socket;
		this.manager = manager;
	}
	//Getter
	public String getUserName() {return username;}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			this.username = (String) in.readObject();
			manager.addClient(this);
			
			Message inputMessage;
			
			while( (inputMessage = (Message)in.readObject()) !=null) {
				String content = "收到來自"+ username + "訊息 :" + inputMessage;
				System.out.println(content);
				manager.broadcast(inputMessage);
			}
		}catch(IOException | ClassNotFoundException e) {
			System.err.println("Runnable Run 錯誤" + e.getMessage());
		}finally {
			manager.removeClient(this);
			closeConnections();
		}
		
	}
	
	private void closeConnections() {
		try {
			if(in != null) in.close();
			if(out != null) out.close();
			if(socket != null && !socket.isClosed()) socket.close(); 
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void sendMessage(Message message) {
		try {
			if(out != null) {
				out.writeObject(message);
				out.flush();
			}
		}catch (IOException e) {
			System.err.println("傳送訊息給 " + username +  " 失敗" + e.getMessage());
		}
	}
	
	
}
