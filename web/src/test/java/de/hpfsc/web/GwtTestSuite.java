package de.hpfsc.web;

import com.google.gwt.junit.tools.GWTTestSuite;
import de.hpfsc.web.widgets.PostingWidgetGwtTC;
import junit.framework.Test;
import junit.framework.TestResult;
import junit.framework.TestSuite;

/**
 * Test suite for GWT-based tests.
 */
public class GwtTestSuite extends GWTTestSuite {

	public static Test suite() {
		final TestSuite suite = new TestSuite("GWT tests");
		suite.addTestSuite(PostingWidgetGwtTC.class);

		return suite;
	}

	@Override
	public void run(TestResult result) {
		// no need to put something here...
	}
}
