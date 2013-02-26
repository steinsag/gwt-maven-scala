package de.hpfsc.web.layout;

import com.google.gwt.user.client.ui.RootPanel;

/**
 * Base layout dividing the overall screen into the following rows:
 * <ol>
 *     <li>page header</li>
 *     <li>list of recent entries</li>
 *     <li>area to post new entry</li>
 *     <li>footer</li>
 * </ol>
 */
public class BaseLayout {
	public static final int COLUMNS_12 = 12;

	public BaseLayout() {
		RootPanel.get("contentWrap").add(new HeaderRow());
		RootPanel.get("contentWrap").add(new RecentEntriesRow());
		RootPanel.get("contentWrap").add(new PostRow());
		RootPanel.get("contentWrap").add(new FooterRow());
	}
}
