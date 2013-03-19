package de.hpfsc.web;

import com.google.gwt.user.client.rpc.AsyncCallback;
import de.hpfsc.shared.DTO.ChatEntry;

import java.util.List;

public interface RoomServiceAsync {
	void get(int roomId, AsyncCallback<List<ChatEntry>> async);
	void post(String postText, AsyncCallback<Void> async);
}
