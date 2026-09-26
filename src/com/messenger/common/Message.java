package com.messenger.common;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable {

	//特定的ID	
	public static final long serialVersionUID = 1L;
	
	//封包裡面要註明是誰送來的
	private String sender;
	//封包訊息內容
	private String content;
	//時間郵戳
	private LocalDateTime timeStamp;
	//訊息類別，是文字、系統還是圖片等
	private String type;
	
	
	//建構子1
	public Message(String sender, String content) {
		this.sender = sender;
		this.content = content;
		this.timeStamp = LocalDateTime.now();
		this.type = "TEXT";
	}
	//建構子2 多型
	public Message(String sender, String content,String type) {
		this.sender = sender;
		this.content = content;
		this.timeStamp = LocalDateTime.now();
		this.type = type;
	}
	
	//Getter 方法
	public String getSender() {return sender;}
	public String getContent() {return content;}
	public LocalDateTime getTimeStamp() {return timeStamp;}
	public String getType() {return type;}
	
	//複寫	
	@Override 
	public String toString() {
		return String.format("[%s] %s %s",timeStamp.toString().substring(11,19),sender,content);
	}
	
	
}
