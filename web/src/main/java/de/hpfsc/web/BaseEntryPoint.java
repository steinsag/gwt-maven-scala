package de.hpfsc.web;

import com.google.gwt.core.client.EntryPoint;
import de.hpfsc.web.layout.BaseLayout;

/**
 * Entry point of the application.
 */
public class BaseEntryPoint implements EntryPoint {
	public void onModuleLoad() {
		new BaseLayout();
	}
}
