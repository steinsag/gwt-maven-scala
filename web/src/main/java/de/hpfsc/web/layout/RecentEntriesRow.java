package de.hpfsc.web.layout;

import com.github.gwtbootstrap.client.ui.Column;
import com.github.gwtbootstrap.client.ui.Paragraph;
import com.github.gwtbootstrap.client.ui.Row;

/**
 * Layout row containing list of recent posts.
 */
public class RecentEntriesRow extends Row {
	public RecentEntriesRow() {
		final Column recentEntriesColumn = new Column(BaseLayout.COLUMNS_12);
		recentEntriesColumn.add(new Paragraph("Recent entries"));
		this.add(recentEntriesColumn);
	}
}
