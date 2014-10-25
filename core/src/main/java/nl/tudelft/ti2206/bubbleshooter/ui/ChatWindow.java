package nl.tudelft.ti2206.bubbleshooter.ui;

import nl.tudelft.ti2206.bubbleshooter.util.ChatObserver;

public class ChatWindow implements ChatObserver {
	ChatMessage msg = new ChatMessage("");

	@Override
	public void addMessage(String msg) {
		msg = new ChatMessage(msg);

	}

}
