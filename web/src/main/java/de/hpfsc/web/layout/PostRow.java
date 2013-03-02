package de.hpfsc.web.layout;

import com.github.gwtbootstrap.client.ui.Column;
import com.github.gwtbootstrap.client.ui.Row;
import de.hpfsc.web.widgets.PostingWidget;

/**
 * Layout row containing all controls needed for posting.
 */
public class PostRow extends Row {
	public PostRow() {
		final Column postColumn = new Column(BaseLayout.COLUMNS_12);
		postColumn.add(new PostingWidget());
		this.add(postColumn);
	}
}
