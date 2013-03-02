package de.hpfsc.web;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Widget;

/**
 * Base class for all GWT tests.
 */
public class BaseGwtTC extends GWTTestCase {
	private static final int RPC_TIMEOUT = 10000;
	protected static final String CHAT_ENTRY_TEXT = "abc def";
	protected static final int ROOM_ID_DEFAULT = 0;
	protected static final int ROOM_ID_WRONG = 100;
	protected static final String DEBUG_FAILURE_EXPECTED = "Received response, but expected failure from server.";
	protected static final String DEBUG_FAILURE_RECEIVED = "Request failure: ";

	/**
	 * Provides a new room REST API service.
	 */
	protected RoomServiceAsync getRoomServiceAsync() {
		RoomServiceAsync roomService = GWT.create(RoomService.class);
		ServiceDefTarget target = (ServiceDefTarget) roomService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "index/room");

		// define RPC timeout
		delayTestFinish(RPC_TIMEOUT);

		return roomService;
	}

	/**
	 * Fire keyUpEvent on given widget (e.g. a text box).
	 */
	protected void fireKeyUpEvent(Widget widget) {
		widget.fireEvent(new GwtEvent<KeyUpHandler>() {
			@Override
			public Type<KeyUpHandler> getAssociatedType() {
				return KeyUpEvent.getType();
			}

			@Override
			protected void dispatch(KeyUpHandler keyUpHandler) {
				keyUpHandler.onKeyUp(null);
			}
		});
	}

	@Override
	public String getModuleName() {
		return "de.hpfsc.indexTest";
	}
}
