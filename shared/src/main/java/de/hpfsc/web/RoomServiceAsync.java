package de.hpfsc.web;

import com.google.gwt.user.client.rpc.AsyncCallback;
import de.hpfsc.shared.DTO.RoomContent;

public interface RoomServiceAsync {
	void post(int roomId, String postText, AsyncCallback<Void> async);

	void get(int roomId, AsyncCallback<RoomContent> async);
}
