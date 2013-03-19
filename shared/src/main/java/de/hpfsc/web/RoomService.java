package de.hpfsc.web;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import de.hpfsc.shared.DTO.ChatEntry;
import de.hpfsc.web.exceptions.RoomNotFoundException;

import java.util.List;

/**
 * REST interface for chat rooms. Implemented methods:
 * <ul>
 *     <li>GET - returns content of chat room</li>
 *     <li>POST - adds a new post to a chat room</li>
 * </ul>
 */
@RemoteServiceRelativePath("room")
public interface RoomService extends RemoteService {
	List<ChatEntry> get(int roomId) throws RoomNotFoundException;

	void post(String postText) throws RoomNotFoundException;
}
