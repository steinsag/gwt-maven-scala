package de.hpfsc.shared.DTO;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a chat entry.
 */
public class ChatEntry implements Serializable {
	private String authorId = null;
	private String text = null;
	private Date timestamp = null;

	@SuppressWarnings("UnusedDeclaration")
	public ChatEntry() {
	}

	public ChatEntry(String authorId, String text, Date timestamp) {
		this.authorId = authorId;
		this.text = text;
		this.timestamp = timestamp;
	}

	public String getAuthorId() {
		return authorId;
	}

	public String getText() {
		return text;
	}

	public Date getTimestamp() {
		return timestamp;
	}
}
