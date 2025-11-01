package org.stofli.jquants.infrastructure.api;

import org.stofli.jquants.infrastructure.api.auth.CredentialProvider;
import org.stofli.jquants.infrastructure.api.auth.RefreshingTokenProvider;
import org.stofli.jquants.infrastructure.api.auth.TokenProvider;
import org.stofli.jquants.infrastructure.config.PropertiesCredentialProvider;

/**
 * Factory for assembling the default HTTP client adapter with its dependencies.
 */
public final class JQuantsClientFactory {

    private JQuantsClientFactory() {
    }

    public static HttpJQuantsClient defaultClient() {
        CredentialProvider credentialProvider = new PropertiesCredentialProvider();
        TokenProvider tokenProvider = new RefreshingTokenProvider(credentialProvider);
        return new HttpJQuantsClient(tokenProvider);
    }
}
