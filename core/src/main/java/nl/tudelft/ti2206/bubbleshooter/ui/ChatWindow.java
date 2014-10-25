package nl.tudelft.ti2206.bubbleshooter.ui;

import nl.tudelft.ti2206.bubbleshooter.util.ChatObserver;
import nl.tudelft.ti2206.bubbleshooter.util.chat.ChatMessage;

public class ChatWindow implements ChatObserver {
	ChatMessage msg = new ChatMessage("");

	@Override
	public void addMessage(String msgstr) {
		this.msg = new ChatMessage(msgstr);

	}

}
