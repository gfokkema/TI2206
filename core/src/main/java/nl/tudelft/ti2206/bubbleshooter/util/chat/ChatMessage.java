package nl.tudelft.ti2206.bubbleshooter.util.chat;


import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ChatMessage implements Serializable {

	private static final long serialVersionUID = 1252600044864413517L;

	private String chat = null;
	private String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
			.format(new Date());
	public String user = "Anonymous";
	private String said = "has said:";

	public ChatMessage(String chat, String user) {
		setUser(user);
		this.chat = timeStamp + user + said + chat;
	}

	public ChatMessage(String chat) {
		this.chat = timeStamp + user + said + chat;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
