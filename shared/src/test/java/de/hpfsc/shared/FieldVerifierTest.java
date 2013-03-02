package de.hpfsc.shared;

import org.junit.Assert;
import org.junit.Test;

public class FieldVerifierTest {
	@Test
	public void testIsValidPost() throws Exception {
		Assert.assertFalse(FieldVerifier.isValidPost(null));
		Assert.assertFalse(FieldVerifier.isValidPost(""));
		Assert.assertTrue(FieldVerifier.isValidPost("a"));
		Assert.assertTrue(FieldVerifier.isValidPost("ab"));
		Assert.assertTrue(FieldVerifier.isValidPost("abc"));
		Assert.assertTrue(FieldVerifier.isValidPost("abc abc abc"));
	}
}
