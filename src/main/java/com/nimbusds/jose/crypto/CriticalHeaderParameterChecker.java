package com.nimbusds.jose.crypto;


import java.util.HashSet;
import java.util.Set;

import com.nimbusds.jose.ReadOnlyHeader;


/**
 * Critical header parameter checker.
 *
 * @author Vladimir Dzhuvinov
 * @version $version$ (2014-04-22)
 */
class CriticalHeaderParameterChecker {


	/**
	 * The critical header parameters to ignore.
	 */
	private Set<String> ignoredCritParams = new HashSet<String>();


	/**
	 * Gets the names of the critical header parameters to ignore.
	 *
	 * @return The names of the critical parameters to ignore. Empty or
	 *         {@code null} if none.
	 */
	public Set<String> getIgnoredCriticalHeaders() {

		return ignoredCritParams;
	}


	/**
	 * Sets the names of the critical header parameters to ignore.
	 *
	 * @param headers The names of the critical parameter to ignore. Empty
	 *                or {@code null} if none.
	 */
	public void setIgnoredCriticalHeaders(final Set<String> headers) {

		ignoredCritParams = headers;
	}


	/**
	 * Returns {@code true} if the specified header passes the critical
	 * parameters check.
	 *
	 * @param header The JWS or JWE header to check. Must not be
	 *               {@code null}.
	 *
	 * @return {@code true} if the header passes, {@code false} if the
	 *         header contains one or more critical header parameters which
	 *         must not be ignored.
	 */
	public boolean headerPasses(final ReadOnlyHeader header) {

		Set<String> crit = header.getCriticalHeaders();

		if (crit == null || crit.isEmpty()) {
			return true; // OK
		}

		if (ignoredCritParams != null && ignoredCritParams.containsAll(crit)) {
			return true; // ok
		}

		return false; // one or more crit params that are not handled
	}
}