package nl.tudelft.ti2206.bubbleshooter.util.chat;

import java.io.Serializable;
import java.util.ArrayList;

public class chat implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5106943752477003211L;
	public ArrayList<ChatMessage> Chatmessage = new ArrayList<ChatMessage>();
	
		
	public chat(){
		
	}
	
	public void addMessage(String msg){
		ChatMessage message = new ChatMessage(msg);
		Chatmessage.add(message);
	}
	
	public String getMessage(int index){
			return Chatmessage.get(index).toString();
	}
	
	public int getLast(){
	//	return Chatmessage.lastIndexOf(Chatmessage);
		return Chatmessage.size()-1;
	}
	
}
