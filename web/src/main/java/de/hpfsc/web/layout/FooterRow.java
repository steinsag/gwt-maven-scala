package de.hpfsc.web.layout;

import com.github.gwtbootstrap.client.ui.Column;
import com.github.gwtbootstrap.client.ui.NavLink;
import com.github.gwtbootstrap.client.ui.NavList;
import com.github.gwtbootstrap.client.ui.Row;

/**
 * Layout row containing complete page footer.
 */
public class FooterRow extends Row {
	public FooterRow() {
		final Column footerColumn = new Column(BaseLayout.COLUMNS_12);
		final NavList footerLinks = new NavList();
		footerLinks.add(new NavLink("Logout", "/j_spring_security_logout"));
		footerColumn.add(footerLinks);
		this.add(footerColumn);
	}
}
