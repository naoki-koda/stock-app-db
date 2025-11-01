package org.stofli.jquants.infrastructure.api.auth;

import java.io.IOException;

/**
 * Strategy for retrieving an access token for the J-Quants API.
 */
public interface TokenProvider {

    String obtainToken() throws IOException, InterruptedException;
}
