package org.stofli.jquants.infrastructure.api.auth;

public interface CredentialProvider {

    JQuantsCredentials loadCredentials();
}
