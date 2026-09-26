package com.messenger.server;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import com.messenger.common.Message;

/**
 * 這個類別扮演「調度中心」的角色。它是一個獨立的物件，
 * 負責維護目前有哪些客戶端在線，並提供「廣播」的功能。
 * 使用 CopyOnWriteArrayList 可以確保多執行緒下的資料安全。
 */

public class ServerManager {
	
	//建立一個連線的客戶清單
	private final List<ClientHandler> clients = new CopyOnWriteArrayList<>();
	private final String senderMsg = "伺服端系統";
	private final String typeMsg = "SYSTEM";
	//有人連線
	public void addClient(ClientHandler client) {
		clients.add(client);
		String content = "系統通知: " + client.getUserName() + " 已經連線，目前在線上.";
		System.out.println(content);
		Message message = new Message(senderMsg,content,typeMsg);
		this.broadcast(message);
	}
	
	//有人離線了
	public void removeClient(ClientHandler client) {
		clients.remove(client);
		String content = "系統通知: " + client.getUserName() + " 已離線";
		System.out.println(content);
		Message message = new Message(senderMsg,content,typeMsg);
		this.broadcast(message);
	}
	
	public void broadcast(Message message) {
		for(ClientHandler client : clients) {
			client.sendMessage(message);
		}
	}
	
}
