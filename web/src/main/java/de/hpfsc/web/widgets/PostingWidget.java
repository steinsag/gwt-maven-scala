package de.hpfsc.web.widgets;

import com.github.gwtbootstrap.client.ui.SubmitButton;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.github.gwtbootstrap.client.ui.WellForm;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.github.gwtbootstrap.client.ui.constants.FormType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import de.hpfsc.shared.FieldVerifier;
import de.hpfsc.web.RoomService;
import de.hpfsc.web.RoomServiceAsync;

/**
 * Widget for posting a new message to the current room.
 * It consists of a text box and a submit button.
 */
public class PostingWidget extends WellForm {

	private final RoomServiceAsync roomService = GWT.create(RoomService.class);

	private final TextBox postBox = new TextBox();
	private SubmitButton postButton;

	public PostingWidget() {
		this.setType(FormType.INLINE);

		// TODO i18n
		postBox
				.setPlaceholder("Type your message and hit return key to post...");
		this.add(postBox);

		// TODO i18n
		postButton = new SubmitButton("Post", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String postText = postBox.getText();

				roomService.post(postText, new AsyncCallback<Void>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("failure: " + caught.getMessage());
					}

					@Override
					public void onSuccess(Void result) {
						Window.alert("success");
					}
				});
			}
		});
		postButton.setType(ButtonType.PRIMARY);
		postButton.setEnabled(false);
		this.add(postButton);

		// disable post button when post box is empty
		postBox.addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				boolean validPost = FieldVerifier
						.isValidPost(postBox.getText());
				postButton.setEnabled(validPost);
			}
		});
	}

	protected SubmitButton getPostButton() {
		return postButton;
	}

	protected TextBox getPostBox() {
		return postBox;
	}
}
