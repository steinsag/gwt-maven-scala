package de.hpfsc.web.widgets;

import com.github.gwtbootstrap.client.ui.SubmitButton;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.user.client.rpc.AsyncCallback;
import de.hpfsc.shared.DTO.ChatEntry;
import de.hpfsc.shared.DTO.RoomContent;
import de.hpfsc.shared.DTO.RoomDetails;
import de.hpfsc.web.BaseGwtTC;
import de.hpfsc.web.RoomServiceAsync;
import de.hpfsc.web.exceptions.RoomNotFoundException;

import java.util.Collection;

public class PostingWidgetGwtTC extends BaseGwtTC {
	/**
	 * State of post button should change based on content of post box.
	 */
	public void testDisablingPostButton() {
		PostingWidget postingWidget = new PostingWidget();
		TextBox postBox = postingWidget.getPostBox();
		SubmitButton postButton = postingWidget.getPostButton();

		// post box and post button should be visible
		assertEquals(true, postBox.isVisible());
		assertTrue(postBox.getText().isEmpty());
		assertEquals(true, postButton.isVisible());

		// post button should be disabled on empty text
		postBox.setText("");
		fireKeyUpEvent(postBox);
		assertEquals(false, postButton.isEnabled());

		// post button should be enabled with some text entered in post box
		postBox.setText(CHAT_ENTRY_TEXT);
		fireKeyUpEvent(postBox);
		assertEquals(true, postButton.isEnabled());

		// post button should be disabled on empty text
		postBox.setText("");
		fireKeyUpEvent(postBox);
		assertEquals(false, postButton.isEnabled());
	}

	public void testGetRoomContent() {
		RoomServiceAsync roomService = getRoomServiceAsync();

		// send request for default room
		roomService.get(ROOM_ID_DEFAULT, new AsyncCallback<RoomContent>() {
			@Override
			public void onFailure(Throwable caught) {
				fail(DEBUG_FAILURE_RECEIVED + caught.getMessage());
			}

			@Override
			public void onSuccess(RoomContent roomContent) {
				assertNotNull(roomContent);
				assertNotNull(roomContent.getChatEntries());

				verifyDefaultRoomDetails(roomContent.getRoomDetails());
				finishTest();
			}
		});
	}

	public void testGetWrongRoom() {
		RoomServiceAsync roomService = getRoomServiceAsync();

		// requesting non-existing room should fail
		roomService.get(ROOM_ID_WRONG, new AsyncCallback<RoomContent>() {
			@Override
			public void onFailure(Throwable caught) {
				assertTrue(caught instanceof RoomNotFoundException);
				finishTest();
			}

			@Override
			public void onSuccess(RoomContent roomContent) {
				fail(DEBUG_FAILURE_EXPECTED);
			}
		});
	}

	public void testPostingValidChatEntry() {
		RoomServiceAsync roomService = getRoomServiceAsync();

		// post chat entry to default room
		roomService.post(ROOM_ID_DEFAULT, CHAT_ENTRY_TEXT,
				new AsyncCallback<Void>() {
					@Override
					public void onFailure(Throwable caught) {
						fail(DEBUG_FAILURE_RECEIVED + caught.getMessage());
					}

					@Override
					public void onSuccess(Void aVoid) {
						finishTest();
					}
				});

		// check that check entry now part of default room
		roomService.get(ROOM_ID_DEFAULT, new AsyncCallback<RoomContent>() {
			@Override
			public void onFailure(Throwable caught) {
				fail(DEBUG_FAILURE_RECEIVED + caught.getMessage());
			}

			@Override
			public void onSuccess(RoomContent roomContent) {
				assertNotNull(roomContent);
				Collection<ChatEntry> chatEntries = roomContent
						.getChatEntries();
				assertNotNull(chatEntries);
				assertEquals(1, chatEntries.size());
				for (ChatEntry chatEntry : chatEntries) {
					assertTrue(chatEntry.getText().equals(CHAT_ENTRY_TEXT));
				}

				verifyDefaultRoomDetails(roomContent.getRoomDetails());
				finishTest();
			}
		});
	}

	public void testPostingToWrongRoom() {
		RoomServiceAsync roomService = getRoomServiceAsync();

		// post chat entry to non-existing room
		roomService.post(ROOM_ID_WRONG, CHAT_ENTRY_TEXT,
				new AsyncCallback<Void>() {
					@Override
					public void onFailure(Throwable caught) {
						assertTrue(caught instanceof RoomNotFoundException);
						finishTest();
					}

					@Override
					public void onSuccess(Void aVoid) {
						fail(DEBUG_FAILURE_EXPECTED);
					}
				});
	}

	private void verifyDefaultRoomDetails(RoomDetails roomDetails) {
		assertNotNull(roomDetails);
		assertEquals(roomDetails.getRoomId(), 0);
		assertEquals(roomDetails.getTitle(), "default room");
	}
}
