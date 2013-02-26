package de.hpfsc.web.layout;

import com.github.gwtbootstrap.client.ui.Column;
import com.github.gwtbootstrap.client.ui.Heading;
import com.github.gwtbootstrap.client.ui.Row;

/**
 * Layout row containing page header.
 */
public class HeaderRow extends Row {
	public HeaderRow() {
		final Column headerColumn = new Column(BaseLayout.COLUMNS_12);
		final Heading heading = new Heading(1, "Simple chat app");
		headerColumn.add(heading);
		this.add(headerColumn);
	}
}
