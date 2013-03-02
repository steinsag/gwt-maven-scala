package de.hpfsc.shared.DTO;

import java.io.Serializable;
import java.util.Collection;

/**
 * Content of a chat room including all chat entries and the room details.
 */
public class RoomContent implements Serializable {
	private RoomDetails roomDetails = null;
	private Collection<ChatEntry> chatEntries = null;

	@SuppressWarnings("UnusedDeclaration")
	public RoomContent() {
	}

	public RoomContent(RoomDetails roomDetails,
			Collection<ChatEntry> chatEntries) {
		this.roomDetails = roomDetails;
		this.chatEntries = chatEntries;
	}

	public RoomDetails getRoomDetails() {
		return roomDetails;
	}

	public Collection<ChatEntry> getChatEntries() {
		return chatEntries;
	}
}
