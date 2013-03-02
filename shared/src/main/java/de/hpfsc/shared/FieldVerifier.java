package de.hpfsc.shared;

/**
 * Definition of validation functions shared between client and server.
 */
public class FieldVerifier {
	/**
	 * Verifies that the specified text makes up a valid chat post.
	 *
	 * @param postText post text to validate
	 * @return true if valid, otherwise false
	 */
	public static boolean isValidPost(final String postText) {
		return postText != null && !postText.trim().isEmpty();
	}
}
