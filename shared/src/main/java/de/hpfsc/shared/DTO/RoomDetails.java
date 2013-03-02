package de.hpfsc.shared.DTO;

import java.io.Serializable;

/**
 * Details about a chat room.
 */
public class RoomDetails implements Serializable {
	private int roomId = 1;
	private String title = "";

	@SuppressWarnings("UnusedDeclaration")
	public RoomDetails() {
	}

	public RoomDetails(int roomId, String title) {
		this.roomId = roomId;
		this.title = title;
	}

	public int getRoomId() {
		return roomId;
	}

	public String getTitle() {
		return title;
	}
}
