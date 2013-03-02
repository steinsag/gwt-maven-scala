package de.hpfsc.web;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import de.hpfsc.shared.DTO.RoomContent;
import de.hpfsc.web.exceptions.RoomNotFoundException;

/**
 * REST interface for chat rooms. Implemented methods:
 * <ul>
 *     <li>GET - returns content of chat room</li>
 *     <li>POST - adds a new post to a chat room</li>
 * </ul>
 */
@RemoteServiceRelativePath("room")
public interface RoomService extends RemoteService {
	RoomContent get(int roomId) throws RoomNotFoundException;

	void post(int roomId, String postText) throws RoomNotFoundException;
}
